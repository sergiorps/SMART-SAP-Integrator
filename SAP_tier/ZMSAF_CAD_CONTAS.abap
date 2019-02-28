FUNCTION ZMSAF_CAD_CONTAS.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_BUKRS) TYPE  BUKRS
*"     VALUE(I_KTOPL) TYPE  KTOPL
*"  TABLES
*"      T_CONTAS STRUCTURE  ZMSAF_CAD_CONTAS
*"----------------------------------------------------------------------
DATA t_SKB1   TYPE TABLE OF SKB1.
DATA w_SKB1   LIKE LINE OF t_SKB1.
DATA t_SKA1   TYPE TABLE OF SKA1 WITH HEADER LINE.
DATA w_SKA1   LIKE LINE OF t_SKA1.
DATA w_dados  TYPE ZMSAF_CAD_CONTAS.

    SELECT BUKRS SAKNR FDLEV
      FROM SKB1
      INTO CORRESPONDING FIELDS OF TABLE t_SKB1
     WHERE bukrs = I_BUKRS.

    if sy-subrc = 0.

      SORT t_SKB1 BY SAKNR ASCENDING.

    SELECT KTOPL SAKNR KTOKS
      FROM SKA1 INTO CORRESPONDING FIELDS OF TABLE t_SKA1
       FOR ALL ENTRIES IN t_SKB1
     WHERE SAKNR EQ t_SKB1-SAKNR
       and KTOPL = I_KTOPL.

    LOOP AT t_SKA1 INTO w_SKA1.
      READ TABLE t_SKB1 INTO w_SKB1 WITH KEY SAKNR = w_SKA1-SAKNR.
      CLEAR w_dados.

      w_dados-ZZTIPOREGISTRO    = 'CTA'.
      w_dados-TACODIGO          = w_SKA1-SAKNR.
      w_dados-TACLASSIFICACAO   = w_SKA1-SAKNR.

      select single TXT50
        into w_dados-TADESCRICAO
        from SKAT
       where KTOPL = w_SKA1-KTOPL
         and SAKNR = w_SKA1-SAKNR
         and SPRAS = 'PT'.

      w_dados-TAGRUPO           = w_SKA1-KTOKS.
      w_dados-TAGRAU            = w_SKB1-FDLEV.
      w_dados-TATIPO            = 'S'.
      w_dados-TATOTALIZADORA    = ''.

      APPEND w_dados TO T_CONTAS.

    ENDLOOP.
    endif.
ENDFUNCTION.