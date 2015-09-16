-- *****************************************************************************

-- *****    EXEMPLO DE ÍNDICE PERSONALIZADO PELO USUÁRIO (Média móvel).    *****


-- Para simplificar a escrita dos índices, alguns trechos são acrescentados automaticamente. 
-- Estes trechos fazem as tarefas mais complicadas de comunicação entre o Grafix(Java) e o script(LUA). 
-- É necessário somente colocar os valores calculados no array predefinido chamado 'dados', conforme código de exemplo abaixo.

-- Estão disponíveis para os índices dois parâmetros inteiros: 'indice:getParam1()' e 'indice:getParam2()'  
-- e um parâmetro booleano: 'indice:getFlag()
-- Estes parâmetros são escolhidos pelo usuário, através da  interface do Grafix, no momento de incluir o índice no eixo.

-- *****************************************************************************


numReg = acao:getNumeroRegistros()
periodo = indice:getParam1()

soma = 0

i = 0
while i < periodo do 
    soma = soma + acao:getClose(i)
    i = i + 1
end
dados[periodo - i] = soma / periodo

i = periodo
while i < numReg do 
    soma = soma + acao:getClose(i) - acao:getClose(i-periodo)
    dados[i] = soma / periodo
    i = i + 1
end

