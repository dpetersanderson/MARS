# Variáveis importantes - Deixar acessível a todo o código

MARS_EXT="asm"  # Qual extensão considerar para o Mars?
MARS_COMMAND="java -Xmx128M -jar Mars.jar nc me ae2 se1 sm"  # Ajustar caminho do java e do Mars.jar


### COMPILE - Colocar na parte de compilação

if [ "$EXT" = "$MARS_EXT" ]; then
	cp $PROBLEMPATH/$UN/$FILENAME.$MARS_EXT $FILENAME.$MARS_EXT
	shj_log "Checking MARS MIPS Syntax"
	$MARS_COMMAND a $FILENAME.$MARS_EXT >/dev/null 2>cerr </dev/null
	EXITCODE=$?
	COMPILE_END_TIME=$(($(date +%s%N)/1000000));
	shj_log "Syntax checked. Exit Code=$EXITCODE  Execution Time: $((COMPILE_END_TIME-COMPILE_BEGIN_TIME)) ms"
	if [ $EXITCODE -ne 0 ]; then
		shj_log "Syntax Error"
		shj_log "$(cat cerr | head -10)"
		echo '<span class="shj_b">Syntax Error</span>' >$PROBLEMPATH/$UN/result.html
		echo '<span class="shj_r">' >> $PROBLEMPATH/$UN/result.html
		(cat cerr | head -10 | sed 's/&/\&amp;/g' | sed 's/</\&lt;/g' | sed 's/>/\&gt;/g' | sed 's/"/\&quot;/g') >> $PROBLEMPATH/$UN/result.html
		echo "</span>" >> $PROBLEMPATH/$UN/result.html
		cd ..
		rm -r $JAIL >/dev/null 2>/dev/null
		shj_finish "Syntax Error"
	fi
fi



### TEST - Colocar na parte de testes

elif [ "$EXT" = "asm" ]; then
    # Opção sb do Mars ativa a sandbox no ambiente de execução
    ./runcode.sh $EXT $MEMLIMIT $TIMELIMIT $TIMELIMITINT $PROBLEMPATH/in/input$i.txt "$MARS_COMMAND sb $FILENAME.$MARS_EXT"
    EXITCODE=$?



### RUNCODE - Modificar no arquivo runcode.sh

# Original:
# Imposing memory limit with ulimit
if [ "$EXT" != "java" ]; then
	ulimit -v $((MEMLIMIT+10000))
	ulimit -m $((MEMLIMIT+10000))
	ulimit -s $((MEMLIMIT+10000))
fi

# Mudar para:
# Imposing memory limit with ulimit
if [ "$EXT" != "java" ] && [ "$EXT" != "$MARS_EXT" ]; then
	ulimit -v $((MEMLIMIT+10000))
	ulimit -m $((MEMLIMIT+10000))
	ulimit -s $((MEMLIMIT+10000))
fi
