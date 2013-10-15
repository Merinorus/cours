create or replace
FUNCTION ADDITION (N1 IN NUMBER, N2 IN NUMBER)
                  RETURN NUMBER IS 
Resultat NUMBER(4);
BEGIN
  Resultat:=N1+N2;
  RETURN Resultat;
END ADDITION;
