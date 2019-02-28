CREATE PROCEDURE ZCADCONTAS
AS
    
DECLARE @V_OBJ INT;
DECLARE @V_URL VARCHAR(200);
DECLARE @V_RESPONSE VARCHAR(8000);
  
SET @V_URL = 'http://10.150.0.12:8080/conector_smart/services/conn_ccontas?method=runJob'
EXEC SP_OACREATE 'MSXML2.ServerXMLHttp', @V_OBJ out
EXEC SP_OAMETHOD @V_OBJ, 'OPEN', NULL, 'GET', @V_URL, FALSE
EXEC SP_OAMETHOD @V_OBJ, 'SEND'
exec SP_OAGETPROPERTY @V_OBJ, 'responseText', @V_RESPONSE out
  
SELECT
zztiporegistro,
tacodigo,
taclassificacao,
tadescricao,
tagrupo,
tagrau,
tatipo,
' ' tanatureza,
tatotalizadora,
' ' tacodigoempresa,
' ' tacontarfb,
' ' taaglutinadora,
' ' taaglutlivre,
' ' tacodigoplano,
' ' TACODIGOTABELA,
' ' ORIGEMDADOS
from dbo.ztemp_ccontas;
  
EXEC SP_OADESTROY @V_OBJ
  
RETURN
