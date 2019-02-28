FUNCTION ZMSAF_CAD_PROD.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_PROD) TYPE  MATNR OPTIONAL
*"     VALUE(I_CENTRO) TYPE  WERKS OPTIONAL
*"     VALUE(I_CENTRO2) TYPE  WERKS OPTIONAL
*"     VALUE(I_DATADE) TYPE  LAEDA OPTIONAL
*"     VALUE(I_DATAATE) TYPE  LAEDA OPTIONAL
*"  TABLES
*"      T_PROD STRUCTURE  ZMSAF_CAD_PROD
*"----------------------------------------------------------------------
DATA t_MARC   TYPE TABLE OF MARC.
DATA w_MARC   LIKE LINE OF t_MARC.
DATA t_MARA   TYPE TABLE OF MARA WITH HEADER LINE.
DATA w_MARA   LIKE LINE OF t_MARA.
DATA w_dados  TYPE ZMSAF_CAD_PROD.

    SELECT MATNR WERKS STEUC
      FROM MARC
      INTO CORRESPONDING FIELDS OF TABLE t_MARC
     WHERE MATNR = I_PROD
       and WERKS between I_CENTRO and I_CENTRO2.

      if sy-subrc is not INITIAL.

    SELECT MATNR WERKS STEUC
      FROM MARC
      INTO CORRESPONDING FIELDS OF TABLE t_MARC
     WHERE MATNR = I_PROD.

      endif.

    SORT t_MARC BY MATNR ASCENDING.

    SELECT MATNR LAEDA MTART MATKL GEWEI
      FROM MARA
      INTO CORRESPONDING FIELDS OF TABLE t_MARA
       FOR ALL ENTRIES IN t_MARC
     WHERE MATNR = t_MARC-MATNR.

    LOOP AT t_MARA INTO w_MARA.
      READ TABLE t_MARC INTO w_MARC WITH KEY MATNR = w_MARA-MATNR.
      CLEAR w_dados.

      w_dados-ZZTIPOREGISTRO        = 'PRO'.
      w_dados-PRSERVICO             = 'M'.
      w_dados-PRCODIGO              = w_MARA-MATNR.

      select single MAKTG
        into w_dados-PRDESCRICAO
        from makt
       where MATNR = w_MARA-MATNR.

      w_dados-PRUNIDADE             = w_MARA-GEWEI.
      w_dados-PRCLASSIFICACAOFISCAL = w_MARC-STEUC.
      w_dados-PRUNIDADEINVENTARIO   = w_MARA-GEWEI.

      select single EAN11
        into w_dados-PRCODIGOBARRA
        from MARM
       where MATNR = w_MARA-MATNR.

      w_dados-PRNATUREZA            = w_MARA-MTART.
      w_dados-PRUNIDADEMEDIDA       = w_MARA-GEWEI.

      APPEND w_dados TO T_PROD.

    ENDLOOP.

ENDFUNCTION.