#!/bin/bash

# script ayant plusieurs noms, chacun associé à une VM précise à créer 
# dans VirtualBox 4 ainsi que son paramétrage :
# script créant une VM Windows XP pontée sur le bridge br0 exploitable dans
# marionnet.
# La VM est construite avec le fichier : 
#             /usr/local/ImagesVirtualBox/last_winxp/2013-01-31-img_xp.vdi
# 
# et :
#   . récupère le port série de l'hôte
#   . a 800 Mo de RAM avec 64 Mo pour la vidéo
#
# version du : 18/03/2013
# auteur : Cyril Pain-Barre

##############################################################################

# Paramètres globaux

# répertoire utilisateur des VM pour VirtualBox 4
VBOX_USER_DIR="$HOME/VirtualBox VMs"


##############################################################################

function isnum {

    echo "$1" | grep -q '^[0-9]\+$'

} # isnum


##############################################################################

function get_vm_parameters_from_script_name {

  # emplacement et nom du fichier image requis pour construire la VM
  ORIG_VDI_DIR="/usr/local/ImagesVirtualBox/last_winxp"
  ORIG_VDI_FILE="2013-01-31-img_xp.vdi"

  # nom de la VM à créer
  TARGET_VM_NAME="vxp4mario"

  # OS de la VM
  TARGET_VM_OSTYPE="WindowsXP"

  # Type d'interface réseau (nat / bridge)
  TARGET_VM_NET_HOW="bridge"
  TARGET_VM_MAC_ADDR="080026662013"

  echo -n "Nom de la VM demandée : $TARGET_VM_NAME "
  echo "($TARGET_VM_OSTYPE avec $TARGET_VM_NET_HOW)"

  # répertoire de la VM (~/'VirtualBox VMs'/nom-vm pour VirtualBox 4)
  TARGET_VM_DIR="$VBOX_USER_DIR/$TARGET_VM_NAME"

  # nom du fichier image de la VM à créer
  TARGET_VM_VDI_FILE="$TARGET_VM_NAME.vdi"

} # get_vm_parameters_from_script_name


##############################################################################

# check_dependencies : vérifie que le fichier image d'origine est présent

function check_dependencies {

    local img_file="$ORIG_VDI_DIR/$ORIG_VDI_FILE"
    if ! [ -f "$img_file" -a -r "$img_file" ]; then
        echo "Fichier image '$img_file' inaccessible en lecture. Abandon."
        exit 1
    fi >> /dev/stderr

} # check_dependencies


##############################################################################

# abort_if_vm_exists () quitte si la VM existe déjà.
#    implémentée comme un simple test d'existence du répertoire
#    TARGET_VM_DIR.

function abort_if_vm_exists {

  if [ -d "$TARGET_VM_DIR" ]; then
    echo "La VM '$TARGET_VM_NAME' semble déjà exister. Abandon."
    echo "Avant de (re)lancer ce script, supprimer cette VM proprement :"
    echo "  1) depuis l'interface de VirtualBox (avec supp. de tous les fichiers)"
    echo "  2) puis, s'il existe toujours, supprimer le répertoire '$TARGET_VM_DIR'"
    exit 1
  fi >> /dev/stderr

} # abort_if_vm_exists


##############################################################################

function create_vm {

  echo "Création du répertoire de la VM"
  mkdir -p "$TARGET_VM_DIR"
  
  echo "Copie de l'image disque (patienter quelques minutes...)"
  pv "$ORIG_VDI_DIR/$ORIG_VDI_FILE" > "$TARGET_VM_DIR/$TARGET_VM_VDI_FILE"
  chmod 660 "$TARGET_VM_DIR/$TARGET_VM_VDI_FILE"
  echo "Image disque copiée"
  
  echo "Création et paramètrages de la VM"
  VBoxManage createvm                           \
                -name "$TARGET_VM_NAME"         \
                -ostype "$TARGET_VM_OSTYPE"    \
                -register
  
  # generalités
  VBoxManage modifyvm "$TARGET_VM_NAME"         \
                --memory "800"                  \
                --vram "64"                     \
                --acpi on                       \
                --audio alsa                    \
                --audiocontroller ac97
  
  # activation du support de l'usb
  VBoxManage modifyvm "$TARGET_VM_NAME" --usb on --usbehci on

  # interface réseau par pont sur br0
  VBoxManage modifyvm "$TARGET_VM_NAME" --nic1 bridged      \
               	--macaddress1 "$TARGET_VM_MAC_ADDR" --bridgeadapter1 br0

  # configuration du port série en utilisant celui de l'hôte
  VBoxManage modifyvm "$TARGET_VM_NAME" --uart1 0x3f8 4 --uartmode1 /dev/ttyS0
  
  ####
  # Gestion du disque dur :

  # ajout d'un contrôleur IDE avec 2 ports
  VBoxManage storagectl "$TARGET_VM_NAME"                                   \
                --name "Contrôleur IDE" --add ide --controller PIIX4
  
  # attachement du disque dur sur le port 0 du contrôleur
  VBoxManage storageattach "$TARGET_VM_NAME"                                \
                --storagectl "Contrôleur IDE" --port 0 --device 0           \
                --type hdd --setuuid ""                                     \
                --medium "$TARGET_VM_DIR/$TARGET_VM_VDI_FILE"
  
  # alors que son port 1 reste vide
  VBoxManage storageattach "$TARGET_VM_NAME"                                \
                --storagectl "Contrôleur IDE" --port 1 --device 0           \
                --type hdd --medium emptydrive
  
  # Fin de la gestion du disque dur
  ####

  # ajout d'un contrôleur de disquettes vide 
  VBoxManage storagectl "$TARGET_VM_NAME"                   \
                --name "Contrôleur disquettes" --add floppy

  VBoxManage storageattach "$TARGET_VM_NAME"                                \
                --storagectl "Contrôleur disquettes" --port 0 --device 0    \
                --type fdd --medium emptydrive
  
  # partage de ~/Bureau/vmshared de l'hôte qui sera 'hostshared' dans la VM
  echo -n "Création du répertoire 'vmshared' (sur le Bureau)"
  echo " pour partager des fichiers avec la VM"
  echo "Dans la VM, ce partage (à activer) s'appellera 'hostshared'"
  mkdir -p "$HOME/Bureau/vmshared"
  VBoxManage sharedfolder add "$TARGET_VM_NAME"     \
                --name hostshared                   \
                --hostpath "$HOME/Bureau/vmshared"

} # create_vm


##############################################################################

#
# corps du script
#

get_vm_parameters_from_script_name 
check_dependencies
abort_if_vm_exists

echo "Construction de la VM '$TARGET_VM_NAME'"
create_vm

if ! ps lx | grep 'VirtualBox$' | grep -qv grep ; then
    echo "Exécution de VirtualBox (pour la gestion des VM)"
    VirtualBox&
else
    echo "VirtualBox (pour gérer les VM) est déjà en cours d'exécution."
fi

echo "Démarrage de la VM '$TARGET_VM_NAME'"
VBoxManage startvm "$TARGET_VM_NAME"


