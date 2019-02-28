FUNCTION ZMSAF_TAB_CCUSTO.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_KOKRS) TYPE  KOKRS
*"  TABLES
*"      T_CCUSTO STRUCTURE  ZMSAF_TAB_CCUSTO
*"----------------------------------------------------------------------
DATA t_csks   TYPE TABLE OF CSKS WITH HEADER LINE.
DATA w_csks   LIKE LINE OF t_csks.
DATA t_cskt   TYPE TABLE OF CSKT.
DATA w_cskt   LIKE LINE OF t_cskt.
DATA w_dados  TYPE ZMSAF_TAB_CCUSTO.

SELECT *
  INTO TABLE t_csks
  FROM CSKS
 WHERE KOKRS = I_KOKRS.

SELECT *
  INTO TABLE t_cskt
  FROM CSKT
  FOR ALL ENTRIES IN t_csks
 WHERE KOKRS = t_csks-KOKRS
   and KOSTL = t_csks-KOSTL.

  LOOP AT t_csks INTO w_csks.
      CLEAR w_dados.
      READ TABLE t_cskt INTO w_cskt WITH KEY KOSTL = w_csks-KOSTL.
      w_dados-TACODIGO              = w_csks-KOSTL.
      w_dados-TACLASSIFICACAO       = w_csks-KOSTL.
      w_dados-TADESCRICAO           = w_cskt-LTEXT.
      APPEND w_dados TO T_CCUSTO.
  ENDLOOP.
ENDFUNCTION.