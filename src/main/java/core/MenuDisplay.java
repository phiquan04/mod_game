package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.joda.time.LocalTime;
import activities.*;
import client.*;
import database.SQL;
import event.EventSpecial;
import io.Message;
import io.SessionManager;
import map.*;
import template.*;

public class MenuDisplay {

    public static int[] ID_MAP_LANG
            = new int[]{1, 9, 17, 25, 33, 41, 49, 66, 69, 79, 83, 93, 107, 113, 191};

    public static void send_menu(Player p, Message m) throws IOException {
        if (!p.isdie) {
            short type = m.reader().readShort();
            // System.out.println("npc id " + type);
            boolean in_map = false;
            for (int i = 0; i < p.map.template.npcs.size(); i++) {
                if (type == p.map.template.npcs.get(i).iditem) {
                    in_map = true;
                    break;
                }
            }
            if (!in_map) {
                return;
            }
            switch (type) {
                case -140: {
                    send_dynamic_menu(p, type, "WIPPER", new String[]{"Chế tạo DIAL", "Thử thách vệ thần"},
                            null);
                    break;
                }
                case -86: {
                    send_dynamic_menu(p, type, "Phó bản",
                            new String[]{"Đá đít Mr3", "Phó bản khổng lồ", "Hướng dẫn Phó bản khổng lồ"},
                            new short[]{150, 142, 148});
                    break;
                }
                case -77: {
                    send_dynamic_menu(p, type, "Ms Gym", new String[]{"BXH Đấu trường", "Hướng dẫn"}, null);
                    break;
                }
                case -73: {
                    send_dynamic_menu(p, type, "Croket", new String[]{"Hướng dẫn"}, null);
                    break;
                }
                case -84: {
                    if (p.clan != null) {
                        if (p.clan.members.get(0).name.equals(p.name)) {
                            if (p.clan.allowRequest == 1) {
                                send_dynamic_menu(p, type, "Băng hải tặc",
                                        new String[]{"Nhiệm vụ băng", "Huy hiệu hành trình", "Phó bản băng",
                                            "Cửa hàng biểu tượng", "Cửa hàng vật phẩm", "Khóa xin vào băng","Xoa bang"},
                                        new short[]{141, 171, 146, 143, 144, 118,118});
                            } else {
                                send_dynamic_menu(p, type, "Băng hải tặc",
                                        new String[]{"Nhiệm vụ băng", "Huy hiệu hành trình", "Phó bản băng",
                                            "Cửa hàng biểu tượng", "Cửa hàng vật phẩm", "Mở xin vào băng","Xoa bang"},
                                        new short[]{141, 171, 146, 143, 144, 118,118});
                            }
                        } else {
                            send_dynamic_menu(p, type, "Băng hải tặc",
                                    new String[]{"Nhiệm vụ băng", "Huy hiệu hành trình", "Phó bản băng"},
                                    new short[]{141, 171, 146});
                        }
                    } else {
                        send_dynamic_menu(p, type, "Băng hải tặc",
                                new String[]{"Đăng ký băng hải tặc (2000 Ruby)", "Hướng dẫn"}, null);
                    }
                    break;
                }
                case -138: { // npc law
                    send_dynamic_menu(p, type, "Cường hóa máu", new String[]{"Phẫu thuật", "Hướng dẫn"},
                            null);
                    break;
                }
                case -106:
                case -91:
                case -71:
                case -48: {
                    switch (p.map.template.id) {
                        case 9: {
                            send_dynamic_menu(p, type, "Zosaku", new String[]{"Săn trùm", "Thách đấu",
                                "Vượt liên ải", "Trận chiến lớn", "Thợ săn hải tặc"},
                                    new short[]{136, 137, 138, 146, 111});
                            break;
                        }
                        case 191:
                        case 189:
                        case 113:
                        case 93:
                        case 69:
                        case 33:
                        case 17: {
                            send_dynamic_menu(p, type, "Zosaku",
                                    new String[]{"Săn trùm", "Thách đấu", "Vượt liên ải", "Trận chiến lớn"},
                                    new short[]{136, 137, 138, 146});
                            break;
                        }
                        case 25: {
                            send_dynamic_menu(p, type, "Zosaku", new String[]{"Săn trùm", "Thách đấu",
                                "Vượt liên ải", "Trận chiến lớn", "Vượt ải đơn"},
                                    new short[]{136, 137, 138, 146, 138});
                            break;
                        }
                        case 41: {
                            send_dynamic_menu(
                                    p, type, "Zosaku", new String[]{"Săn trùm", "Thách đấu", "Vượt liên ải",
                                        "Trận chiến lớn", "Bảo vệ kho báu Namie"},
                                    new short[]{136, 137, 138, 146, 139});
                            break;
                        }
                        case 49: {
                            send_dynamic_menu(p, type, "Zosaku", new String[]{"Săn trùm", "Thách đấu",
                                "Vượt liên ải", "Trận chiến lớn", "Lệnh truy nã"},
                                    new short[]{136, 137, 138, 146, 160});
                            break;
                        }
                        case 83: {
                            send_dynamic_menu(p, type, "Zosaku",
                                    new String[]{"Săn trùm", "Thách đấu", "Vượt siêu liên ải", "Trận chiến lớn"},
                                    new short[]{136, 137, 159, 146});
                            break;
                        }
                    }
                    break;
                }
       case -72: { // npc nami
                    if (p.map.template.id == 17) {
                        send_dynamic_menu(p, type, "Nami",
                                new String[]{"Đổi Ruby", "Thành tích hằng ngày", "Tích lũy nạp thẻ", "Đấu giá",
                                    "Điểm nạp tích lũy", "Thông tin bản thân", "Chợ mua bán"},
                                new short[]{132, 134, 110, 169, 170, 136, 152});
                    } else {
                        send_dynamic_menu(
                                p, type, "Nami", new String[]{"Đổi Ruby", "Thành tích hằng ngày",
                                    "Tích lũy nạp thẻ", "Đấu giá", "Điểm nạp tích lũy", "Thông tin bản thân"},
                                new short[]{132, 134, 110, 169, 170, 136});
                    }
                    break;
                }
                case -997: {
                    switch (p.map.template.id) {
                        case 1: { // lang coi xay gio
                            send_dynamic_menu(p, type, "Hướng dẫn", new String[]{"Đăng ký tài khoản",
                                "Nhiệm vụ tân thủ", "Vật phẩm", "Vận buôn", "Trang bị", "Kỹ năng"}, null);
                            break;
                        }
                        case 9: { // thi tran vo so
                            send_dynamic_menu(p, type, "Hướng dẫn",
                                    new String[]{"Bảng xếp hạng", "Nhiệm vụ hàng ngày", "Cường hóa trang bị",
                                        "Khảm đá", "Chuyển hóa", "Săn trùm", "Phó bản liên tầng", "Phó bản PvP",
                                        "Khóa bảo vệ", "Nạp tiền"},
                                    null);
                            break;
                        }
                        case 17: { // thi tran orange
                            send_dynamic_menu(p, type, "Hướng dẫn",
                                    new String[]{"Chợ mua bán", "Vòng xoay kho báu", "Hoàn mỹ", "Kích ẩn",
                                        "Thuộc tính kích ẩn (1-4)", "Thuộc tính kích ẩn (5-8)",
                                        "Thuộc tính kích ẩn (9-13)"},
                                    null);
                            break;
                        }
                        case 25: { // sirup
                            send_dynamic_menu(p, type, "Hướng dẫn", new String[]{"Cường hóa ác quỷ"}, null);
                            break;
                        }
                        case 33: { // barati
                            send_dynamic_menu(p, type, "Hướng dẫn", new String[]{"Băng hải tặc", "Phó bản băng",
                                "Phó bản khổng lồ", "Bảo vệ pháo đài"}, null);
                            break;
                        }
                        case 41: { // hat de
                            send_dynamic_menu(p, type, "Hướng dẫn",
                                    new String[]{"Bảo vệ kho báu Namie", "Siêu boss"}, null);
                            break;
                        }
                        case 49: { // khoi dau
                            send_dynamic_menu(p, type, "Hướng dẫn", new String[]{"Lệnh truy nã", "Siêu boss"},
                                    null);
                            break;
                        }
                        case 66: { // mom sinh doi
                            send_dynamic_menu(p, type, "Hướng dẫn", new String[]{"Vượt Redline"}, null);
                            break;
                        }
                        case 69: { // whiskey
                            send_dynamic_menu(p, type, "Hướng dẫn",
                                    new String[]{"Trái ác quỷ", "Đấu trường tự do", "Siêu boss"}, null);
                            break;
                        }
                        case 79: { // little grand
                            send_dynamic_menu(p, type, "Hướng dẫn",
                                    new String[]{"Đá đít Mr.3", "Phó bản khổng lồ"}, null);
                            break;
                        }
                    }
                    break;
                }
                case -100: {
                    send_dynamic_menu(p, type, "Sự kiện",
                            new String[]{"T/g x2 kỹ năng EXP", "T/g khóa exp", "Hủy t/g khóa exp", "Tài xỉu"},
                            null);
                    break;
                }
                case -37: {
                    send_dynamic_menu(p, type, "Bếp trưởng", new String[]{"Học Skill", "Tẩy tiềm năng",
                        "Xóa nội tại", "Người giới thiệu", "Đá hành trình"},
                            new short[]{123, 124, 125, 138, 127});
                    break;
                }
                case -4: {
                    if (p.level >= 100) {
                        send_dynamic_menu(p, type, "Gap", new String[]{"Học Skill", "Tẩy tiềm năng",
                            "Xóa nội tại", "Người giới thiệu", "Thông thạo"},
                                new short[]{123, 124, 125, 138, 138});
                    } else {
                        send_dynamic_menu(p, type, "Gap",
                                new String[]{"Học Skill", "Tẩy tiềm năng", "Xóa nội tại", "Người giới thiệu"},
                                new short[]{123, 124, 125, 138});
                    }
                    break;
                }
                // case -144: // kinh do nuoc
                case -153:
                case -152:
                case -151:
                case -150:
                case -149:
                case -148: {
                    MenuActions.Show_List_Map_Tele(p, 0, -144);
                    break;
                }
                // case -124: // thi tran thien su
                case -131:
                case -130:
                case -129:
                case -128:
                case -127:
                case -126:
                case -125:
                case -123: {
                    MenuActions.Show_List_Map_Tele(p, 0, -124);
                    break;
                }
                case -115:
                case -114:
                case -113:
                case -112:
                case -111:
                case -110:
                case -109:
                case -108: {
                    MenuActions.Show_List_Map_Tele(p, 0, -107);
                    break;
                }
                case -96:
                case -94:
                case -93:
                case -92: {
                    MenuActions.Show_List_Map_Tele(p, 0, -85);
                    break;
                }
                case -83:
                case -81:
                case -80:
                case -79: {
                    MenuActions.Show_List_Map_Tele(p, 0, 0);
                    break;
                }
                case -59:
                case -63:
                case -62:
                case -61: {
                    MenuActions.Show_List_Map_Tele(p, 0, -60);
                    break;
                }
                case -58:
                case -51:
                case -50:
                case -49: {
                    MenuActions.Show_List_Map_Tele(p, 0, -44);
                    break;
                }
                case -57:
                case -42:
                case -41:
                case -40: {
                    MenuActions.Show_List_Map_Tele(p, 0, -36);
                    break;
                }
                case -56:
                case -34:
                case -33:
                case -32: {
                    MenuActions.Show_List_Map_Tele(p, 0, -28);
                    break;
                }
                case -55:
                case -26:
                case -25:
                case -24: {
                    MenuActions.Show_List_Map_Tele(p, 0, -20);
                    break;
                }
                case -54:
                case -18:
                case -17:
                case -16: {
                    MenuActions.Show_List_Map_Tele(p, 0, -12);
                    break;
                }
                case -53:
                case -10:
                case -9:
                case -8: {
                    // send_dynamic_menu(p, type, "Nhiệm vụ", new String[] {"Nhiệm vụ chính", "Nhiệm
                    // vụ lặp"}, null);
                    MenuActions.Show_List_Map_Tele(p, 0, -5);
                    break;
                }
                case -6: {
                    Service.Send_UI_Shop(p, 99);
                    break;
                }
                case -145:
                case -122:
                case -118:
                case -103:
                case -87:
                case -74:
                case -67:
                case -45:
                case -31:
                case -21:
                case -13:
                case -1: {
                    if (p.conn.user.length() > 12 && p.conn.user.startsWith("htth_vietvan_")) {
                        send_dynamic_menu(
                                p, type, get_name_npc(type), new String[]{"Đăng ký tài khoản", "Thách đấu",
                            "Cao thủ", "Băng hải tặc", "Truy nã", "Đá hành trình", "Quà tặng tân thủ"},
                                null);
                    } else {
                        send_dynamic_menu(p, type, get_name_npc(type), new String[]{"Thách đấu", "Cao thủ",
                            "Băng hải tặc", "Truy nã", "Đá hành trình", "Quà tặng tân thủ"}, null);
                    }
                    break;
                }
                case -133: {
                    send_dynamic_menu(p, type, "Kho Báu",
                            new String[]{"Vòng quay kho báu", "Hoàn mỹ - Kích ẩn", "Vòng quay ốc sên"}, null);
                    break;
                }
                case -105:
                case -90:
                case -70:
                case -47: {
                    if (p.map.template.id == 25) { // cuong hoa ac quy
                        send_dynamic_menu(p, type, "Johny",
                                new String[]{"Cường Hóa", "Khảm đá", "Chuyển hóa", "Ghép mảnh trang bị",
                                    "Cường hóa thời trang", "Cường hóa ác quỷ"},
                                new short[]{126, 127, 128, 126, 126, 154});
                    } else {
                        send_dynamic_menu(
                                p, type, "Johny", new String[]{"Cường Hóa", "Khảm đá", "Chuyển hóa",
                                    "Ghép mảnh trang bị", "Cường hóa thời trang"},
                                new short[]{126, 127, 128, 126, 126});
                    }
                    break;
                }
                case -147:
                case -120:
                case -116:
                case -102:
                case -89:
                case -76:
                case -68:
                case -46:
                case -39:
                case -29:
                case -22:
                case -14:
                case -2: {
                    send_dynamic_menu(
                            p, type, get_name_npc(type), new String[]{"Quán ăn", "Vận Chuyển Hàng", "Tiệm tóc",
                        "Đóng thuyền", "Thời trang", "Thẩm mỹ viện"},
                            new short[]{104, 107, 106, 105, 108, 158});
                    break;
                }
                case -144: // kinh do nuoc
                case -124: // thi tran thien su
                case -132: // dao jaza
                case -107: // thi tran nanohano
                case -85: // thi tran horn
                case -97: // dao little grand
                case 0: // thi tran whiskey
                case -82: // mom sinh doi
                case -60: // thi tran khoi dau
                case -44: // lang hat de
                case -36: // nha hang barati
                case -28: // lang sirup
                case -20: // thi tran orang
                case -12: // thi tran vo so
                case -5: { // lang coi xay gio
                    send_dynamic_menu(p, type, "", new String[]{"Trong làng", "Thế giới"});
                    break;
                }
                case -7: {
                    MenuActions.Menu_Change_Zone(p);
                    break;
                }
                case -146:
                case -121:
                case -117:
                case -101:
                case -88:
                case -75:
                case -69:
                case -38:
                case -30:
                case -23:
                case -15:
                case -3: {
                    send_dynamic_menu(p, type, get_name_npc(type),
                            new String[]{Clazz.NAME[p.clazz - 1], "Hệ khác",
                                (!p.is_show_hat ? "Bật hiển thị nón" : "Tắt hiển thị nón"), "Khóa bảo vệ",
                                "Thùng rác"},
                            new short[]{Clazz.ICON[p.clazz - 1], 116, 117, 118, 113});
                    break;
                }
                case -119: {
                    break;
                }
                default: {
                    send_dynamic_menu(p, type, (get_name_npc(type) + " id " + type), new String[]{"Chưa có"},
                            new short[]{117});
                    break;
                }
            }
        }
    }

    private static String get_name_npc(int type) {
        switch (type) {
            case -145:
                return "Icebug";
            case -122:
                return "Gan";
            case -118:
                return "Cricket";
            case -103:
                return "Cobran";
            case -87:
                return "Daltont";
            case -74:
                return "Mr Opera";
            case -67:
                return "Mastersun";
            case -45:
                return "Genzo";
            case -31:
                return "Băng hải tặc nhí";
            case -1:
                return "Trưởng làng";
            case -146:
                return "Paule";
            case -147:
                return "kookoroo";
            case -120:
                return "Conic";
            case -121:
                return "Pagada";
            case -117:
                return "Spect";
            case -116:
                return "Terri";
            case -101:
                return "Kohzak";
            case -102:
                return "Yoshi moto";
            case -89:
                return "Dr Kure";
            case -88:
                return "Stook";
            case -75:
                return "Ms Vivi";
            case -76:
                return "Mr Acrobatic";
            case -68:
                return "Sapie";
            case -46:
                return "Noziko";
            case -39:
                return "Cami";
            case -29:
                return "Kaiya";
            case -21:
                return "Thị Trưởng";
            case -13:
                return "Cobi";
            case -69:
                return "Masu";
            case -3:
                return "Guru";
            case -15:
                return "Mẹ Rita";
            case -23:
                return "Poroy";
            case -30:
                return "Merri";
            case -38:
                return "Partty";
            case -2:
                return "Machiko";
            case -14:
                return "Rita";
            case -22:
                return "Cho Cho";
        }
        return "NPC";
    }

    public static void send_dynamic_menu(Player p, int id_npc, String name_npc, String[] list_menu,
            short[] list_icon) throws IOException {
        if (!p.isdie) {
            Message m = new Message(-20);
            if (list_icon == null) {
                m.writer().writeByte(0);
            } else {
                m.writer().writeByte(5);
            }
            m.writer().writeShort(id_npc);
            m.writer().writeByte(0);
            m.writer().writeUTF(name_npc);
            m.writer().writeByte(list_menu.length);
            for (int i = 0; i < list_menu.length; i++) {
                m.writer().writeUTF(list_menu[i]);
                if (list_icon != null) {
                    m.writer().writeShort(list_icon[i]);
                }
            }
            p.conn.addmsg(m);
            m.cleanup();
        }
    }

    static void send_dynamic_menu(Player p, int id_npc, String name_npc, String[] list_menu,
            byte[] list_icon, int b) throws IOException {
        if (!p.isdie) {
            Message m = new Message(-20);
            m.writer().writeByte(3);
            m.writer().writeShort(id_npc);
            m.writer().writeByte(1);
            m.writer().writeUTF(name_npc);
            m.writer().writeByte(list_menu.length);
            for (int i = 0; i < list_menu.length; i++) {
                m.writer().writeUTF(list_menu[i]);
                m.writer().writeShort(list_icon[i]);
                m.writer().writeByte(b);
            }
            p.conn.addmsg(m);
            m.cleanup();
        }
    }

    static void send_dynamic_menu(Player p, int id_npc, String name_npc,
            List<String> list_menu, List<Integer> list_icon) throws IOException {
        if (!p.isdie) {
            Message m = new Message(-20);
            m.writer().writeByte(4);
            m.writer().writeShort(id_npc);
            m.writer().writeByte(0);
            m.writer().writeUTF(name_npc);
            m.writer().writeByte(list_menu.size());
            for (int i = 0; i < list_menu.size(); i++) {
                m.writer().writeUTF(list_menu.get(i));
                m.writer().writeShort(list_icon.get(i));
            }
            p.conn.addmsg(m);
            m.cleanup();
        }
    }

    static void send_dynamic_menu(Player p, int idNPC, String title, String[] name)
            throws IOException {
        if (!p.isdie) {
            Message m = new Message(-20);
            m.writer().writeByte(2);
            m.writer().writeShort(idNPC);
            m.writer().writeByte(0);
            m.writer().writeUTF(title);
            m.writer().writeByte(name.length);
            for (int i = 0; i < name.length; i++) {
                m.writer().writeUTF(name[i]);
            }
            p.conn.addmsg(m);
            m.cleanup();
        }
    }

    static void send_dynamic_menu(Player p, int idNPC, String title, int[] name)
            throws IOException {
        if (!p.isdie) {
            Message m = new Message(-20);
            m.writer().writeByte(1);
            m.writer().writeShort(idNPC);
            m.writer().writeByte(0);
            m.writer().writeUTF(title);
            m.writer().writeByte(name.length);
            for (int i = 0; i < name.length; i++) {
                Map map = Map.get_map_by_id(name[i])[0];
                m.writer().writeUTF(map.template.name);
                m.writer().writeByte(map.template.id == p.map.template.id ? 4 : 2);
                m.writer().writeByte(7);
            }
            p.conn.addmsg(m);
            m.cleanup();
        }
    }
}
