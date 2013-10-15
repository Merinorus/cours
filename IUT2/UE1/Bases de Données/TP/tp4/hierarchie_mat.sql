create or replace
FUNCTION HIERARCHIE_MAT(CODEMAT IN VARCHAR2)
         RETURN VARCHAR2 AS 
CURSOR hier IS SELECT LIBELLE
               FROM MODULE
               CONNECT BY CODE=PRIOR CODEPERE
               START WITH CODE=CODEMAT;
str VARCHAR2(255);
countMat NUMBER(1,0) DEFAULT 0;
matNotExist EXCEPTION;
BEGIN

  SELECT COUNT(CODE) INTO countMat
  FROM MODULE
  WHERE CODE=CODEMAT;
  
  IF countMat = 0 THEN
    RAISE matNotExist;
  END IF;

  FOR mat IN hier LOOP
    str:=str||'/'||mat.LIBELLE;
  END LOOP;
  RETURN str;
EXCEPTION
  WHEN matNotExist THEN DBMS_OUTPUT.PUT_LINE('La matière n''existe pas.'); RETURN sqlcode;
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(sqlcode); RETURN sqlcode;
END HIERARCHIE_MAT;
