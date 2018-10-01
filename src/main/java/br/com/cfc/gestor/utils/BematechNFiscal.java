package br.com.cfc.gestor.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BematechNFiscal extends Library{

	public static final int CORTE_TOTAL     = 1;
	public static final int CORTE_PARCIAL   = 0;
	public static final int ERRO_NO_COMANDO = 0;
	
    public BematechNFiscal Instance = (BematechNFiscal) Native.loadLibrary("mp2032", BematechNFiscal.class);

    /**
     * <summary>Seleciona  largura da bitola do papel da impressora.</summary>
     * @param width - bitola do papel em milímetros. Podendo ser: 48, 58, 76, 80 ou 112.
     * @return [INTEIRO (0|1)] - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int AjustaLarguraPapel(int width);
    
    /**
     * Aciona a guilhotina, cortando o papel em modo parcial ou total.
     * @param modo 0 = acionamento parcial, 1 = acionamento total.
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int AcionaGuilhotina(int modo);

    /**
     * Envio de comandos para a impressora, como por exemplo: comandos de Autenticação, comando para Acionamento de Gaveta, etc.
     * @param command <type>STRING</type> comando que deseja executar
     * @param size INTEIRO tamanho do comando que será enviado
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int ComandoTX(String command, int size);
    
    /**
     * Configurar o modelo da impressora não fiscal em uso. <b>IMPORTANTE: Esta função deve ser usada antes da função IniciaPorta</b>
     * @param modelo INTEIRO 
     * <ul>
     * 	<li> 0 = MP-20 TH, MP-2000 CI ou MP-2000 TH </li>
     *  <li> 1 = MP-20 MI, MP-20 CI ou MP-20 S</li>
     *  <li> 2 = Blocos térmicos (com comunicacao serial DTR/DSR)</li> 
     *  <li> 3 = Bloco 112 mm</li>
     *  <li> 4 = ThermalKiosk</li>
     *  <li> 5 = MP-4000 TH</li>
     *  <li> 7 = MP-4200 TH</li>
     *  <li> 8 = MP-2500 TH</li>
     * </ul> 
     *   0 = Default
     * @return
     */
    public int ConfiguraModeloImpressora(int modelo);
    
    /**
     * Configura a quantidade de linhas impressas no extrato, antes de começar a espulsá-lo (eject). A quantidade de linhas pode variar de 1 a 150 linhas. O Default é 90 linhas. OBS: SOMENTE UTILIZADA PARA OS BLOCOS IMPRESSORES
     * @param tamanho INTEIRO tamanho do extrato
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int ConfiguraTamanhoExtrato(int tamanho);
    
    /**
     * Esta função segura a execução do Aplicativo, até que todo o texto enviado seja impresso.
     * @param modo INTEIRO modo de espera.
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int EsperaImpressao(int modo);
    
    /**
     * Esta função tem por objetivo fechar a porta de comunicação, liberando a porta para outras atividades.
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int FechaPorta();

    /**
     * Esta função tem por objetivo enviar textos para a impressora, com formatações, informadas pelos parâmetros.
     * @param texto texto a ser impresso.
     * @param tipoletra INTEIRO sendo 1 = comprimido, 2 = normal, 3 = elite
     * @param italic INTEIRO  0 = desativa modo, 1 = ativa modo.
     * @param sublin INTEIRO 0 = desativa modo, 1 = ativa modo.
     * @param expand INTEIRO 0 = desativa modo, 1 = ativa modo.
     * @param enfat INTEIRO 0 = desativa modo, 1 = ativa modo.
     * @return Indica se a função conseguiu enviar o comando para impressora.
     */
    public int FormataTX(String texto, int tipoletra, int italic, int sublin, int expand, int enfat);

    /**
     * Esta função habilita ou desabilita a quantidade de linhas, configurada na função ConfiguraTamanhoExtrato. Caso esta função não for executada, a quantidade de linhas não será a Default (90 linhas), será a quantidade que for enviada. OBS: SOMENTE UTILIZADA PARA BLOCOS IMPRESSORES
     * @param extratoLongo INTEIRO 0 = desabilitado, 1 = habilitado
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int HabilitaExtratoLongo(int extratoLongo);
    
    /**
     * Esta função tem por objetivo abrir a porta de comunicação, onde a impressora está conectada
     * @param porta STRING nome da porta de comunicação
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int IniciaPorta(String porta);
    
    /**
     * Esta função tem por objetivo retornar o estado da impressora.
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int Le_Status();
    
    /**
     * Verifica se há papel posicionado no Presenter.
     * @return INTEIRO - Indica se a função conseguiu enviar o comando para impressora.
     */
    public int VerificaPapelPresenter();
}
