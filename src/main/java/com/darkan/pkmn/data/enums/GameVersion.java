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
    /// Pokémon Sapphire (GBA)
    S(1),
    /// Pokémon Ruby (GBA)
    R(2),
    /// Pokémon Emerald (GBA)
    E(3),
    /// Pokémon FireRed (GBA)
    FR(4),
    /// Pokémon LeafGreen (GBA)
    LG(5),
    /// Pokémon Colosseum &amp; Pokémon XD (GameCube)
    CXD(15),

    /**
     * Gen 4
     */
    /// Pokémon Diamond (NDS)
    D(10),
    /// Pokémon Pearl (NDS)
    P(11),
    /// Pokémon Platinum (NDS)
    Pt(12),
    /// Pokémon Heart Gold (NDS)
    HG(7),
    /// Pokémon Soul Silver (NDS)
    SS(8),

    /**
     * Gen 5
     */
    /// Pokémon White (NDS)
    W(20),
    /// Pokémon Black (NDS)
    B(21),
    /// Pokémon White 2 (NDS)
    W2(22),
    /// Pokémon Black 2 (NDS)
    B2(23),

    /**
     * Gen 6
     */
    /// Pokémon X (3DS)
    X(24),
    /// Pokémon Y (3DS)
    Y(25),
    /// Pokémon Alpha Sapphire (3DS)
    AS(26),
    /// Pokémon Omega Ruby (3DS)
    OR(27),

    /**
     * Gen 7
     */
    /// Pokémon Sun (3DS)
    SN(30),
    /// Pokémon Moon (3DS)
    MN(31),
    /// Pokémon Ultra Sun (3DS)
    US(32),
    /// Pokémon Ultra Moon (3DS)
    UM(33),
    /// Pokémon GO (GO -> Lets Go transfers)
    GO(34),

    /**
     * Virtual Console (3DS) Gen1
     */
    /// Pokémon Red (3DS Virtual Console)
    RD(35),
    /// Pokémon Green[JP]/Blue[INT] (3DS Virtual Console)
    GN(36),
    /// Pokémon Blue[JP] (3DS Virtual Console)
    BU(37),
    /// Pokémon Yellow [JP] (3DS Virtual Console)
    YW(38),

    /**
     * Virtual Console (3DS) Gen2
     */
    /// Pokémon Gold (3DS Virtual Console)
    GD(39),
    /// Pokémon Silver (3DS Virtual Console)
    SV(40),
    /// Pokémon Crystal (3DS Virtual Console)
    C(41),

    /**
     * Nintendo Switch
     */
    /// Pokémon Let's Go Pikachu (NX)
    GP(42),

    /// Pokémon Let's Go Eevee (NX)
    GE(43),

    /// Pokémon Sword (NX)
    SW(44),

    /// Pokémon Shield (NX)
    SH(45);
	
	private int id;
	
	private GameVersion(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
