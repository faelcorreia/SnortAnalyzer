package banco;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Consultas {
	public static String[] connectionString() {
		String user = "", password = "", dbname = "", host = "", aux = "";
		String[] conn = new String[3];
		try {
			URL file = new Consultas().getClass().getResource(
					"../../dbconnection");
			BufferedReader buff = new BufferedReader(new FileReader(
					URLDecoder.decode(file.getPath(), "UTF-8")));
			while (buff.ready()) {
				aux = buff.readLine();
				if (aux.matches("user=.*")) {
					user = aux.substring(5);
				} else if (aux.matches("password=.*")) {
					password = aux.substring(9);
				} else if (aux.matches("dbname=.*")) {
					dbname = aux.substring(7);
				} else if (aux.matches("host=.*")) {
					host = aux.substring(5);
				}
			}
			if (user == "") {
				user = "snort";
			}
			if (dbname == "") {
				dbname = "snort";
			}
			if (host == "") {
				host = "snort.local";
			}
			conn[0] = "jdbc:mysql://" + host + "/" + dbname;
			conn[1] = user;
			conn[2] = password;
			return conn;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Connection criaConexao() {
		String[] cs = connectionString();
		Connection conn = null;
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(cs[0], cs[1], cs[2]);
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static ResultSet query(String q) {
		try {
			Connection conn = criaConexao();
			ResultSet rs = conn.createStatement().executeQuery(q);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<GraficoPadrao> retornaGraficoPadrao(String query) {
		try {
			ResultSet rs = query(query);
			ArrayList<GraficoPadrao> top10 = new ArrayList<GraficoPadrao>();
			while (rs.next()) {
				int quantidade = rs.getInt("QUANTIDADE");
				String nome = rs.getString("NOME");
				top10.add(new GraficoPadrao(quantidade, nome));
			}
			return top10;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<GraficoPadrao> retornaGraficoPadraoIP(String query) {
		try {
			ResultSet rs = query(query);
			ArrayList<GraficoPadrao> top10 = new ArrayList<GraficoPadrao>();
			while (rs.next()) {
				int quantidade = rs.getInt("QUANTIDADE");
				String nome = Util.longToIP(rs.getLong("NOME"));
				top10.add(new GraficoPadrao(quantidade, nome));
			}
			return top10;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Evento> retornaTabelaEventos() {
		try {
			ResultSet rs = query("SELECT e.sid, e.cid, e.timestamp, ip.ip_src, ip.ip_dst, "
					+ "t.tcp_sport, u.udp_sport, t.tcp_dport, u.udp_dport, s.sig_name, s.sig_priority "
					+ "FROM event e LEFT JOIN tcphdr t ON (e.sid = t.sid AND e.cid = t.cid) "
					+ "LEFT JOIN udphdr u ON (e.sid = u.sid AND e.cid = u.cid), iphdr ip, signature s "
					+ "WHERE e.sid=ip.sid AND e.cid=ip.cid AND e.signature = s.sig_id;");
			ArrayList<Evento> todos = new ArrayList<Evento>();
			int sid;
			int cid;
			Date timestamp;
			String ip_src;
			String ip_dst;
			int sport;
			int dport;
			int protocolo;
			String signature;
			int priority;
			while (rs.next()) {
				sid = rs.getInt("sid");
				cid = rs.getInt("cid");
				timestamp = rs.getDate("timestamp");
				ip_src = Util.longToIP(rs.getLong("ip_src"));
				ip_dst = Util.longToIP(rs.getLong("ip_dst"));

				if (rs.getInt("udp_sport") == 0) {
					protocolo = Evento.TCP;
					sport = rs.getInt("tcp_sport");
					dport = rs.getInt("tcp_dport");
				} else {
					protocolo = Evento.UDP;
					sport = rs.getInt("udp_sport");
					dport = rs.getInt("udp_dport");
				}

				signature = rs.getString("sig_name");
				priority = rs.getInt("sig_priority");

				todos.add(new Evento(sid, cid, timestamp, ip_src, ip_dst,
						sport, dport, protocolo, signature, priority));
			}
			return todos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<GraficoPadrao> getTop10Categorias() {
		return retornaGraficoPadrao("SELECT COUNT(*) \"quantidade\", sig_name \"nome\" "
				+ "FROM signature s, event e "
				+ "WHERE e.signature=s.sig_id "
				+ "GROUP BY sig_name " + "ORDER BY quantidade desc LIMIT 0,10;");

	}

	public static ArrayList<GraficoPadrao> getTop10Classes() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", sig_class_name \"nome\" "
				+ "FROM event e, signature s, sig_class sc "
				+ "WHERE e.signature=s.sig_id AND s.sig_class_id=sc.sig_class_id "
				+ "GROUP BY nome " + "ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTopPrioridades() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", sig_priority \"nome\" "
				+ "FROM event e, signature s "
				+ "WHERE e.signature=s.sig_id "
				+ "GROUP BY nome " + "ORDER BY quantidade;");
	}

	public static ArrayList<GraficoPadrao> getTop10EnderecosDestinos() {
		return retornaGraficoPadraoIP("SELECT count(*) \"quantidade\", ip_dst \"nome\" "
				+ "FROM event e, iphdr i "
				+ "WHERE e.sid=i.sid AND e.cid = i.cid "
				+ "GROUP BY nome "
				+ "ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTop10EnderecosFontes() {
		return retornaGraficoPadraoIP("SELECT count(*) \"quantidade\", ip_src \"nome\" "
				+ "FROM event e, iphdr i "
				+ "WHERE e.sid=i.sid AND e.cid = i.cid "
				+ "GROUP BY nome "
				+ "ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTop10TCPPortasDestinos() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", tcp_dport \"nome\" "
				+ "FROM event e, tcphdr t "
				+ "WHERE e.sid=t.sid AND e.cid=t.cid "
				+ "GROUP BY nome ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTop10TCPPortasFontes() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", tcp_sport \"nome\" "
				+ "FROM event e, tcphdr t "
				+ "WHERE e.sid=t.sid AND e.cid=t.cid "
				+ "GROUP BY nome ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTop10UDPPortasDestinos() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", udp_dport \"nome\" "
				+ "FROM event e, udphdr u "
				+ "WHERE e.sid=u.sid AND e.cid=u.cid "
				+ "GROUP BY nome ORDER BY quantidade desc LIMIT 0,10;");
	}

	public static ArrayList<GraficoPadrao> getTop10UDPPortasFontes() {
		return retornaGraficoPadrao("SELECT count(*) \"quantidade\", udp_sport \"nome\" "
				+ "FROM event e, udphdr u "
				+ "WHERE e.sid=u.sid AND e.cid=u.cid "
				+ "GROUP BY nome ORDER BY quantidade desc LIMIT 0,10;");
	}
}
