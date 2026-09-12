package io;

/**
 * Named opcodes for {@link Message#cmd}, replacing the magic numbers that used to appear
 * directly in the client/MessageHandler switch and in a few protocol-framing checks.
 * Names are inferred from the handler each opcode dispatches to; where the original meaning
 * was not recoverable from the code, the constant is still named after its observed use so the
 * number itself never has to be re-guessed again.
 */
public final class Cmd {
    private Cmd() {}

    // --- connection / handshake / login ---
    public static final int HANDSHAKE = -27;
    public static final int LOGIN = -9;
    public static final int LOGIN_LEGACY = -2;
    public static final int CREATE_CHAR = -8;
    public static final int CHECK_DATA_VER = -6;
    public static final int REQUEST_DATA_UPDATE = -7; // multiplexed by a sub-type byte; also reused server->client for clock sync
    public static final int READ_PART_NEW = -82;
    public static final int SEND_DATA_FROM_SERVER = -38;
    public static final int SEND_CHAR_LIST = -4;
    public static final int LOGIN_RETRY_LATER = -69;
    public static final int SPECIAL_ACCOUNT_ECHO = -57; // sent only for usernames prefixed "htth_vietvan_"

    // bulk data pushed to the client at login (data/datafromsver/x*), needs the 4-byte length
    // framing in Session.send_msg/read_msg because payloads can exceed a short's range
    public static final int DATA_PUSH_39 = -39;
    public static final int DATA_PUSH_93 = -93;
    public static final int DATA_PUSH_76 = 76;
    public static final int SEND_ICON_LARGE = -101; // icon payload >= 32KB, same 4-byte framing as above
    public static final int SEND_ICON = -51; // icon payload < 32KB, normal 2-byte framing

    // --- map / movement / world ---
    public static final int MAP_READY = 0; // client finished entering the map
    public static final int MOVE = 1;
    public static final int USE_SKILL = 2;
    public static final int REQUEST_MOB_INFO = 4;
    public static final int REQUEST_LIVE_FROM_DIE = 6;
    public static final int PICK_ITEM = 12;
    public static final int CHANGE_PK_FLAG = 14;
    public static final int MAP_CHAT = 17;
    public static final int CHAT = 18;
    public static final int BUFF = 20;
    public static final int AREA_SELECT = 23;
    public static final int VIEW_OTHER_PLAYER = -42;
    public static final int REQUEST_CHAR_INFO_IN_MAP = -5;
    public static final int UPDATE_NUM_PLAYER_IN_MAP = -70;
    public static final int CHECK_PLAY_IN_MAP = 46;
    public static final int TELEPORT_TO_PLAYER = -36; // teleport to friend/enemy, costs ruby

    // --- combat / pvp ---
    public static final int PVP = -63;
    public static final int UPDATE_PK_POINT = -45;
    public static final int AUTO_REVIVE = -71;

    // --- menu / UI ---
    public static final int MENU_PROCESS = -20;
    public static final int MENU_REQUEST = -19;
    public static final int CLIENT_YES_NO = -11;
    public static final int CLIENT_INPUT = -58;
    public static final int TABLE_TICK_OPTION = -74;
    public static final int TOGGLE_EQUIP_DISPLAY = 43; // show/hide hat or fashion weapon
    public static final int REFRESH_APPEARANCE = 68; // cycles tocSuper and re-broadcasts charWearing

    // --- inventory / items ---
    public static final int USE_ITEM = -22;
    public static final int USE_POTION = -13;
    public static final int BUY_ITEM = -18;
    public static final int SELL_ITEM = -21;
    public static final int UPGRADE_ITEM = -48;
    public static final int UPGRADE_SUPER_ITEM = 66;
    public static final int UPGRADE_DEVIL = 45;
    public static final int UPGRADE_DIAL = -94;
    public static final int UPGRADE_SKIN = 81;
    public static final int REBUILD_ITEM = -67;
    public static final int SPLIT_JOIN_ITEM = -50;
    public static final int PLAYER_CHEST = -32;
    public static final int ITEM4_INFO_REQUEST = -105;
    public static final int SEND_OBJ_TEMPLATE = 48;
    public static final int PLUS_STAT_POINT = -16;
    public static final int LEARN_SKILL = -28;
    public static final int SKILL_INFO_REQUEST = 74;

    // --- social ---
    public static final int FRIEND = -29;
    public static final int PARTY = -25;
    public static final int CLAN = -52;
    public static final int CLAN_CHAT_BROADCAST = -31;
    public static final int CHAT_KTG = -46; // paid global/clan chat
    public static final int TRADE = -49;

    // --- systems / features ---
    public static final int DANH_HIEU = -102; // title / badge
    public static final int TOP_UP_LIST = -90;
    public static final int WANTED = -85;
    public static final int WANTED_CHEST = -86;
    public static final int FIGHT = -35;
    public static final int PET = -80;
    public static final int JOURNEY = 79; // "Hanh Trinh"
    public static final int SHIP = -53;
    public static final int MAX_LEVEL = 49;
    public static final int EVENT_SPECIAL = 80;
    public static final int MARKET = 44;
    public static final int QUEST = -23;
    public static final int RED_LINE = -72;
    public static final int RANKING = -30; // "BXH" leaderboard
    public static final int BOAT_SHOP_UPDATE = -62;
    public static final int LUCKY_SPIN = 54; // "Vong Quay"
    public static final int CHUYEN_HOA = -77;
    public static final int RMS_PROCESS = -33;
    public static final int FASHION_EFFECT_TOGGLE = -47;

    public static final int AUCTION = -91; // "dau gia" - handler currently empty/unimplemented
    public static final int UNUSED_95 = -95; // handler currently empty
}
