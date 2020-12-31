package com.darkan.pkmn.data.enums;

public enum GameVersion {
	 /**
     * Indicators for method empty arguments & result indication. Not stored values.
     */
    Invalid(-2),
    Any(-1),
    Unknown(0),

    // The following values are IDs stored within PKM data), and can also identify individual games.

    /**
     * Gen 3
     */
    /// Pok�mon Sapphire (GBA)
    S(1),
    /// Pok�mon Ruby (GBA)
    R(2),
    /// Pok�mon Emerald (GBA)
    E(3),
    /// Pok�mon FireRed (GBA)
    FR(4),
    /// Pok�mon LeafGreen (GBA)
    LG(5),
    /// Pok�mon Colosseum &amp; Pok�mon XD (GameCube)
    CXD(15),

    /**
     * Gen 4
     */
    /// Pok�mon Diamond (NDS)
    D(10),
    /// Pok�mon Pearl (NDS)
    P(11),
    /// Pok�mon Platinum (NDS)
    Pt(12),
    /// Pok�mon Heart Gold (NDS)
    HG(7),
    /// Pok�mon Soul Silver (NDS)
    SS(8),

    /**
     * Gen 5
     */
    /// Pok�mon White (NDS)
    W(20),
    /// Pok�mon Black (NDS)
    B(21),
    /// Pok�mon White 2 (NDS)
    W2(22),
    /// Pok�mon Black 2 (NDS)
    B2(23),

    /**
     * Gen 6
     */
    /// Pok�mon X (3DS)
    X(24),
    /// Pok�mon Y (3DS)
    Y(25),
    /// Pok�mon Alpha Sapphire (3DS)
    AS(26),
    /// Pok�mon Omega Ruby (3DS)
    OR(27),

    /**
     * Gen 7
     */
    /// Pok�mon Sun (3DS)
    SN(30),
    /// Pok�mon Moon (3DS)
    MN(31),
    /// Pok�mon Ultra Sun (3DS)
    US(32),
    /// Pok�mon Ultra Moon (3DS)
    UM(33),
    /// Pok�mon GO (GO -> Lets Go transfers)
    GO(34),

    /**
     * Virtual Console (3DS) Gen1
     */
    /// Pok�mon Red (3DS Virtual Console)
    RD(35),
    /// Pok�mon Green[JP]/Blue[INT] (3DS Virtual Console)
    GN(36),
    /// Pok�mon Blue[JP] (3DS Virtual Console)
    BU(37),
    /// Pok�mon Yellow [JP] (3DS Virtual Console)
    YW(38),

    /**
     * Virtual Console (3DS) Gen2
     */
    /// Pok�mon Gold (3DS Virtual Console)
    GD(39),
    /// Pok�mon Silver (3DS Virtual Console)
    SV(40),
    /// Pok�mon Crystal (3DS Virtual Console)
    C(41),

    /**
     * Nintendo Switch
     */
    /// Pok�mon Let's Go Pikachu (NX)
    GP(42),

    /// Pok�mon Let's Go Eevee (NX)
    GE(43),

    /// Pok�mon Sword (NX)
    SW(44),

    /// Pok�mon Shield (NX)
    SH(45);
	
	private int id;
	
	private GameVersion(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
