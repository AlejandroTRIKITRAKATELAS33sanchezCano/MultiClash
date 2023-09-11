package funcionalidad.enumeraciones;

/**
 * Ataques
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public enum Ataque {

	/**
	 * Ataques de tipo Animal
	 */
	EMBESTIDA(15), RABIA(30), ARAÑAZO(28), GOLPE_COLA(29), ATAQUE_MAXIMO(30), COLA_HIERRO(20), GRITO(25), DESCANSO(27),

	/**
	 * Ataques de tipo Inteligencia
	 */
	ANALISIS(25), ALGEBRA_LINEAL(15), DIVISION_ENTRE_0(20), DERIVAR(30), INTEGRAR(29), VOLERTE_ATEO(28), BOFETADA_FINA(30), ESPANTAVIEJAS(29),

	/**
	 * Ataques de tipo Perturbador
	 */
	EXE(15), SUSTO(23), A_MIMIR(26), PESADILLA(21), MORDIDA(20), RISA_MACABRA(28), MALDICION(27), PIROTECNIA(30),

	/**
	 * Ataques de tipo Guapo
	 */
	CANTO(25), DOBLE_DE_RIESGO(15), ATAQUE_MAMA(30), REPROBARTE(26), HILO_EN_TWITTER(27), ROMPECORAZON(23), DEMANDA(20), PEINARSE(21),

	/**
	 * Ataques de tipo peleador
	 */
	GOPLE_BAJO(30), CON_LA_SILLA(23), BOFETADA(15), ATAQUE_ALA(22), GOLPE_AEREO(25), KAMEKAMEHA(24), SUPLEX(28), PATADA_RAPIDA(21);

	private int energia;

	private Ataque(int energia) {
		this.energia = energia;
	}

	public int getEnergia() {
		return energia;
	}
}
