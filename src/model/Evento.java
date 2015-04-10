package model;

import java.util.Date;

public class Evento {
	private int sid;
	private int cid;
	private Date timestamp;
	private String ip_src;
	private String ip_dst;
	private int sport;
	private int dport;
	private int protocolo;
	private String signature;
	private int priority;
	public static int TCP = 1;
	public static int UDP = 2;

	public Evento(int _sid, int _cid, Date _timestamp, String _ip_src,
			String _ip_dst, int _sport, int _dport, int _protocolo,
			String _signature, int _priority) {
		sid = _sid;
		cid = _cid;
		timestamp = _timestamp;
		ip_src = _ip_src;
		ip_dst = _ip_dst;
		sport = _sport;
		dport = _dport;
		protocolo = _protocolo;
		signature = _signature;
		priority = _priority;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getDport() {
		return dport;
	}

	public void setDport(int dport) {
		this.dport = dport;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getIp_src() {
		return ip_src;
	}

	public void setIp_src(String ip_src) {
		this.ip_src = ip_src;
	}

	public String getIp_dst() {
		return ip_dst;
	}

	public void setIp_dst(String ip_dst) {
		this.ip_dst = ip_dst;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public int getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
