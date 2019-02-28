FUNCTION ZMSAF_CAD_CLIFOR.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_BUKRS) TYPE  BUKRS
*"     VALUE(I_DATADE) TYPE  ERDAT
*"     VALUE(I_DATAATE) TYPE  ERDAT
*"  TABLES
*"      T_CLIFOR STRUCTURE  ZMSAF_CAD_CLIFOR
*"----------------------------------------------------------------------
DATA t_LFB1   TYPE TABLE OF LFB1.
DATA t_LFA1   TYPE TABLE OF LFA1 WITH HEADER LINE.
DATA w_LFA1   LIKE LINE OF t_LFA1.
DATA t_KNB1   TYPE TABLE OF KNB1.
DATA t_KNA1   TYPE TABLE OF KNA1 WITH HEADER LINE.
DATA w_KNA1   LIKE LINE OF t_KNA1.
DATA w_dados  TYPE ZMSAF_CAD_CLIFOR.

*** Fornecedores
    SELECT lifnr bukrs akont erdat
      FROM lfb1
      INTO CORRESPONDING FIELDS OF TABLE t_LFB1
     WHERE bukrs = I_BUKRS
       and ERDAT between I_DATADE and I_DATAATE.

      SORT t_LFB1 BY lifnr ASCENDING.

    SELECT lifnr land1 name1 name2 ort01 ort02 pstlz regio adrnr
           erdat stcd1 stcd2 telf1 telfx stkzn stcd3 stcd4 updat
           stenr stras
      FROM lfa1 INTO CORRESPONDING FIELDS OF TABLE t_lfa1
       FOR ALL ENTRIES IN t_lfb1
     WHERE lifnr EQ t_lfb1-lifnr.

*** Clientes
    SELECT kunnr bukrs akont erdat
      FROM knb1
      INTO CORRESPONDING FIELDS OF TABLE t_KNB1
     WHERE bukrs = I_BUKRS
       and ERDAT between I_DATADE and I_DATAATE.

      SORT t_KNB1 BY kunnr ASCENDING.

    SELECT kunnr land1 name1 name2 ort01 pstlz regio stras telf1
           telfx adrnr erdat ort02 stcd1 stcd2 stcd3 stcd4 updat
           stceg name3 name4 loevm
      FROM kna1 INTO CORRESPONDING FIELDS OF TABLE t_KNA1
       FOR ALL ENTRIES IN t_knb1
     WHERE kunnr EQ t_knb1-kunnr.

  LOOP AT t_lfa1 INTO w_LFA1.
      CLEAR w_dados.
      w_dados-ZZTIPOREGISTRO         = 'PES'.
      w_dados-CFCODIGO               = w_LFA1-lifnr.
      w_dados-CFCGCCPF               = w_LFA1-STCD1.
      w_dados-CFINSCRICAOESTADUAL    = w_LFA1-STCD2.
      w_dados-CFINSCRICAOMUNICIPAL   = w_LFA1-STCD3.
      w_dados-CFRAZAOSOCIAL          = w_LFA1-NAME1.
      w_dados-CFNOMEFANTASIA         = w_LFA1-NAME2.
      w_dados-CFNOMELOGRADOURO       = w_LFA1-STRAS.
      w_dados-CFBAIRRO               = w_LFA1-ORT02.
      w_dados-CFMUNICIPIO            = w_LFA1-ORT01.
      w_dados-CFUNIDADEFEDERACAO     = w_LFA1-REGIO.
      w_dados-CFCEP                  = w_LFA1-PSTLZ.

      select single COD_PAIS
        into w_dados-CFCODIGOPAIS
        from J_1BEFD_COUNTRY
       where LAND1 = w_LFA1-land1.

      select single LANDX
        into w_dados-CFPAIS
        from T005T
       where LAND1 = w_LFA1-land1.

      w_dados-CFCODIGOIBGE           = ''.
      w_dados-CFCATEGORIARELACIONADA = 'F'.
      APPEND w_dados TO T_CLIFOR.
  ENDLOOP.

  LOOP AT t_KNA1 INTO w_KNA1.
      CLEAR w_dados.
      w_dados-ZZTIPOREGISTRO         = 'PES'.
      w_dados-CFCODIGO               = w_KNA1-KUNNR.
      w_dados-CFCGCCPF               = w_KNA1-STCD1.
      w_dados-CFINSCRICAOESTADUAL    = w_KNA1-STCD2.
      w_dados-CFINSCRICAOMUNICIPAL   = w_KNA1-STCD3.
      w_dados-CFRAZAOSOCIAL          = w_KNA1-NAME1.
      w_dados-CFNOMEFANTASIA         = w_KNA1-NAME2.
      w_dados-CFNOMELOGRADOURO       = w_KNA1-STRAS.
      w_dados-CFBAIRRO               = w_KNA1-ORT02.
      w_dados-CFMUNICIPIO            = w_KNA1-ORT01.
      w_dados-CFUNIDADEFEDERACAO     = w_KNA1-REGIO.
      w_dados-CFCEP                  = w_KNA1-PSTLZ.

      select single COD_PAIS
        into w_dados-CFCODIGOPAIS
        from J_1BEFD_COUNTRY
       where LAND1 = w_KNA1-land1.

      select single LANDX
        into w_dados-CFPAIS
        from T005T
       where LAND1 = w_KNA1-land1.

      w_dados-CFCODIGOIBGE           = ''.
      w_dados-CFCATEGORIARELACIONADA = 'C'.
      APPEND w_dados TO T_CLIFOR.
  ENDLOOP.

ENDFUNCTION.