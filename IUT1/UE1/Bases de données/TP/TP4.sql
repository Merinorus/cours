-- Q1
SELECT DISTINCT NUMCL, NOM
FROM CLIENT CL
WHERE NOT EXISTS (SELECT RES.IDV
                  FROM RESERVATION RES
                  JOIN VOYAGE VOY ON VOY.IDV=RES.IDV
                  WHERE RES.NUMCL=CL.NUMCL
                  AND PAYSARR='MAROC');
                  
SELECT DISTINCT NUMCL, NOM
FROM CLIENT
WHERE NUMCL NOT IN (SELECT NUMCL
                    FROM RESERVATION RES
                    JOIN VOYAGE VOY ON VOY.IDV=RES.IDV
                    WHERE PAYSARR='MAROC');
                    
SELECT NUMCL, NOM
FROM CLIENT
MINUS
SELECT CL.NUMCL, NOM
FROM CLIENT CL
JOIN RESERVATION RES ON RES.NUMCL=CL.NUMCL
JOIN VOYAGE VOY ON RES.IDV=VOY.IDV
WHERE PAYSARR='MAROC';

-- Q2
SELECT CL.NUMCL, NOM, RES.IDV, DATERES
FROM CLIENT CL, RESERVATION RES
WHERE RES.NUMCL(+)=CL.NUMCL
AND RES.IDV IS NULL
UNION
SELECT CL.NUMCL, NOM, RES.IDV, DATERES
FROM CLIENT CL
JOIN RESERVATION RES ON RES.NUMCL=CL.NUMCL
WHERE DATERES<'10/11/03';

-- Q3
SELECT CL.NUMCL, NOM
FROM CLIENT CL
MINUS
SELECT CL.NUMCL, NOM
FROM CLIENT CL
JOIN RESERVATION RES ON RES.NUMCL=CL.NUMCL
WHERE DATERES<'10/11/03';

-- Q4
-- Pour chaque client, la liste éventuelle des voyages partant de la ville où ils habitent
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE=VOY.VILLEDEP(+);

-- Pour chaque voyage, la liste éventuelle des clients habitant dans la ville de départ
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE(+)=VOY.VILLEDEP;

-- Q5
-- Pour chaque client, la liste éventuelle des voyages à destination du Kenya et partant de la ville où ils habitent
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE=VOY.VILLEDEP(+)
AND VOY.PAYSARR='KENYA';

-- Pour chaque voyage, la liste éventuelle des clients > 2200 habitant dans la ville de départ
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE(+)=VOY.VILLEDEP
AND CL.NUMCL>2200;

-- Q6
-- La liste des clients habitant dans une ville d'où aucun voyage ne part
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE=VOY.VILLEDEP(+)
AND VOY.VILLEDEP IS NULL;

-- La liste des voyages partant d'une ville dans laquelle aucun client n'habite
SELECT CL.NUMCL, CL.VILLE, VOY.IDV, VOY.VILLEDEP, VOY.PAYSARR
FROM CLIENT CL, VOYAGE VOY
WHERE CL.VILLE(+)=VOY.VILLEDEP
AND CL.NUMCL IS NULL;

-- Q7
SELECT DISTINCT HOTEL, NBETOILES
FROM VOYAGE
WHERE NBETOILES >= ALL (SELECT NBETOILES FROM VOYAGE);

-- Q8
SELECT PAYSARR
FROM VOYAGE
MINUS
SELECT PAYSARR
FROM VOYAGE
WHERE NBETOILES >= ALL (SELECT NBETOILES FROM VOYAGE);

-- Q9
SELECT PAYSARR, COUNT(*) VOYAGES
FROM VOYAGE
GROUP BY PAYSARR;

-- Q10
SELECT PAYSARR, VILLEARR, COUNT(*) VOYAGES
FROM VOYAGE
GROUP BY PAYSARR, VILLEARR;

-- Q11
SELECT PAYSARR, COUNT(DISTINCT VILLEARR) VILLES
FROM VOYAGE
GROUP BY PAYSARR;

-- Q12
SELECT VOY.IDV, VOY.VILLEARR, COUNT(PL.DATEDEP) DATES
FROM VOYAGE VOY
JOIN PLANNING PL ON PL.IDV=VOY.IDV
GROUP BY VOY.IDV, VOY.VILLEARR;

-- Q13
SELECT VOY.IDV, VOY.VILLEARR, COUNT(CAR.CODE) OPTIONS
FROM VOYAGE VOY
JOIN CARAC CAR ON CAR.IDV=VOY.IDV
WHERE PRIX IS NULL
GROUP BY VOY.IDV, VOY.VILLEARR;

-- Q14
SELECT CATEGORIE, COUNT(NUMCL) CLIENTS
FROM CLIENT
GROUP BY CATEGORIE;

-- Q15
SELECT VOY.IDV, VOY.VILLEARR, COUNT(RES.IDV) RESERVATIONS, SUM(NVL(NBPERS,0)+NVL(NBENF,0)) PERSONNES
FROM VOYAGE VOY
JOIN RESERVATION RES ON RES.IDV=VOY.IDV
GROUP BY VOY.IDV, VOY.VILLEARR;

-- Q16
SELECT CAR.IDV, AVG(NVL(PRIX,0)) PRIXMOY
FROM CARAC CAR
JOIN OPTIONV OPT ON OPT.CODE=CAR.CODE
GROUP BY CAR.IDV;

-- Q17
SELECT VOY.IDV, VOY.VILLEARR, (SELECT COUNT(*) FROM CARAC WHERE PRIX IS NULL AND IDV=VOY.IDV) OPTGRAT,
                              (SELECT COUNT(*) FROM CARAC WHERE PRIX IS NOT NULL AND IDV=VOY.IDV) OPTPAYANTES
FROM VOYAGE VOY
ORDER BY VOY.IDV, VOY.VILLEARR;

-- Q18
SELECT VILLE, COUNT(NUMCL) CLIENTS
FROM CLIENT
GROUP BY VILLE
HAVING COUNT(NUMCL) > 5;

-- Q19
SELECT VOY.IDV, VOY.PAYSARR, SUM(NVL(TARIF,0)*NVL(NBPERS,0) + NVL(TARIFENF,0)*NVL(NBENF,0)) TOTAL
FROM VOYAGE VOY
JOIN RESERVATION RES ON RES.IDV=VOY.IDV
JOIN PLANNING PL ON PL.IDV=VOY.IDV
GROUP BY VOY.IDV, VOY.PAYSARR;

-- Q20
SELECT VOY.IDV, VOY.PAYSARR, SUM(NVL(TARIF,0)*NVL(NBPERS,0) + NVL(TARIFENF,0)*NVL(NBENF,0)) TOTAL
FROM VOYAGE VOY
JOIN RESERVATION RES ON RES.IDV=VOY.IDV
JOIN PLANNING PL ON PL.IDV=VOY.IDV AND RES.DATEDEP=PL.DATEDEP
GROUP BY VOY.IDV, VOY.PAYSARR;

-- Q21
SELECT PAYSARR, COUNT(*) RESERVATIONS
FROM RESERVATION RES
JOIN VOYAGE VOY ON RES.IDV=VOY.IDV
GROUP BY PAYSARR
HAVING COUNT(*) > (SELECT COUNT(*)
                   FROM RESERVATION RES
                   JOIN VOYAGE VOY ON RES.IDV=VOY.IDV
                   WHERE PAYSARR='ESPAGNE');
                   
-- Q22
SELECT CATEGORIE, COUNT(*) CLIENTS
FROM CLIENT
GROUP BY CATEGORIE
HAVING COUNT(*) >= ALL (SELECT COUNT(*)
                        FROM CLIENT
                        GROUP BY CATEGORIE);
                        
-- Q23
SELECT PAYSARR, COUNT(*) PAYS
FROM RESERVATION RE1
JOIN VOYAGE VO1 ON RE1.IDV=VO1.IDV
GROUP BY PAYSARR
HAVING COUNT(*) >= ALL (SELECT COUNT(*)
                        FROM RESERVATION RE2
                        JOIN VOYAGE VO2 ON RE2.IDV=VO2.IDV
                        GROUP BY PAYSARR);
                        
-- Q24
SELECT CL.NUMCL, CL.NOM, COUNT(*) RESERVATIONS
FROM CLIENT CL
JOIN RESERVATION RE1 ON RE1.NUMCL=CL.NUMCL
WHERE CL.NUMCL IN (SELECT NUMCL
                   FROM RESERVATION RE2
                   JOIN VOYAGE VOY ON RE2.IDV=VOY.IDV
                   WHERE VOY.PAYSARR='KENYA')
GROUP BY CL.NUMCL, CL.NOM;

-- Q25
SELECT PAYSARR, COUNT(DISTINCT HOTEL) HOTELS
FROM VOYAGE
WHERE PAYSARR IN (SELECT PAYSARR
                  FROM VOYAGE
                  WHERE NBETOILES=5)
GROUP BY PAYSARR;