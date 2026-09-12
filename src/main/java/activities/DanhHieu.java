/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package activities;

import client.Player;
import core.Service;
import io.Message;
import java.util.ArrayList;
import template.Option;

/**
 *
 * @author Administrator
 */
public class DanhHieu {
    public static ArrayList<DanhHieu> ENY = new ArrayList<>();
    public int id;
    public String Name;
    public int idicon;
    public byte nframe;
    public ArrayList<Option> op = new ArrayList<>();

    public static void process(Message m, Player p) {
        try {
            byte type = m.reader().readByte();
            int id = m.reader().readInt();
            byte action = m.reader().readByte();
            switch (type) {
                case 0:{
                    Message msg = new Message(-102);
                    msg.writer().writeByte(type);
                    msg.writer().writeByte(ENY.size());
                    for (DanhHieu danhHieu : ENY) {
                        msg.writer().writeInt(danhHieu.id);
                        msg.writer().writeUTF(danhHieu.Name);
                        msg.writer().writeInt(danhHieu.idicon);
                        msg.writer().writeByte(danhHieu.nframe);
                        msg.writer().writeByte((p.id_danh_hieu == danhHieu.id ? 2:1));
                        msg.writer().writeByte(danhHieu.op.size());
                        for (Option op : danhHieu.op) {
                            msg.writer().writeByte(op.id);
                            msg.writer().writeInt(op.getParamGoc());
                        }
                    }
                    p.conn.addmsg(msg);
                    msg.cleanup();
                    break;
                }
                case 1:{
                    if(action ==  0){
                        Message msg = new Message(-102);
                        msg.writer().writeByte(type);
                        msg.writer().writeByte(0);
                        msg.writer().writeInt(p.id);
                        msg.writer().writeInt(get_Id(id).idicon);
                        msg.writer().writeInt(get_Id(id).nframe);
                        p.map.send_msg_all_p(msg, null, true);
                        msg.cleanup();
                        msg = new Message(-102);
                        msg.writer().writeByte(2);
                        msg.writer().writeInt(id);
                        msg.writer().writeByte(2);
                        p.conn.addmsg(msg);
                        msg.cleanup();
                         Service.send_box_ThongBao_OK(p, "Sử dụng thành công");
                         p.id_danh_hieu = id;
                         p.update_info_to_all();
                    }else{
                        Message msg = new Message(-102);
                        msg.writer().writeByte(type);
                        msg.writer().writeByte(1);
                        msg.writer().writeInt(p.id);
                        p.map.send_msg_all_p(msg,null,true);
                        msg.cleanup();
                        msg = new Message(-102);
                        msg.writer().writeByte(2);
                        msg.writer().writeInt(id);
                        msg.writer().writeByte(1);
                        p.conn.addmsg(msg);
                        msg.cleanup();
                        Service.send_box_ThongBao_OK(p, "Tháo thành công");
                        p.id_danh_hieu  = -1;
                        p.update_info_to_all();
}

                    break;
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    public static DanhHieu get_Id(int id){
        for(DanhHieu dh:ENY){
            if(dh.id == id){
                return dh;
            }
        }
        return null;
    }
}