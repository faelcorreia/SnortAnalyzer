package controller;

import java.util.ArrayList;

import model.*;
import banco.Consultas;

public class Graficos {
	public static String retornaTabelaEvento() {
		String str = "";
		ArrayList<Evento> eventos = Consultas.retornaTabelaEventos();
		if (eventos != null) {
			Evento e;
			str = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"display\" id=\"tabela\">";
			str += "<thead><tr>";
			str += "<th>CID</th>";
			str += "<th>Prioridade</th>";
			str += "<th>Hora</th>";
			str += "<th>Assinatura</th>";
			str += "<th>Endereço fonte</th>";
			str += "<th>Porta fonte</th>";
			str += "<th>Endereço Destino</th>";
			str += "<th>Porta Destino</th>";
			str += "<th>Protocolo</th></thead>";
			str += "<tbody>";
			for (int i = 0; i < eventos.size(); i++) {
				e = eventos.get(i);
				str += "<tr class=\"odd gradeA\">";
				str += "<td>" + e.getCid() + "</td>";
				str += "<td>" + e.getPriority() + "</td>";
				str += "<td>" + e.getTimestamp() + "</td>";
				str += "<td>" + e.getSignature() + "</td>";
				str += "<td>" + e.getIp_src() + "</td>";
				str += "<td>" + e.getSport() + "</td>";
				str += "<td>" + e.getIp_dst() + "</td>";
				str += "<td>" + e.getDport() + "</td>";
				str += "<td>" + (e.getProtocolo()==Evento.TCP?"TCP":"UDP") + "</td>";
				str += "</tr>";
			}
			str += "</tbody>";
			str += "<tfoot><tr>";
			str += "<th>CID</th>";
			str += "<th>Prioridade</th>";
			str += "<th>Hora</th>";
			str += "<th>Assinatura</th>";
			str += "<th>Endereço fonte</th>";
			str += "<th>Porta fonte</th>";
			str += "<th>Endereço Destino</th>";
			str += "<th>Porta Destino</th>";
			str += "<th>Protocolo</th>";
			str += "</tfoot>";
			str += "</table>";			
		}
		return str;
	}

	public static String retornaGrafico(int tipo) {
		if (tipo == 1)
			return retornaDados(Consultas.getTop10Categorias());
		if (tipo == 2)
			return retornaDados(Consultas.getTop10Classes());
		if (tipo == 3)
			return retornaDados(Consultas.getTop10EnderecosDestinos());
		if (tipo == 4)
			return retornaDados(Consultas.getTop10EnderecosFontes());
		if (tipo == 5)
			return retornaDados(Consultas.getTop10TCPPortasDestinos());
		if (tipo == 6)
			return retornaDados(Consultas.getTop10TCPPortasFontes());
		if (tipo == 7)
			return retornaDados(Consultas.getTop10UDPPortasDestinos());
		if (tipo == 8)
			return retornaDados(Consultas.getTop10UDPPortasFontes());
		if (tipo == 9)
			return retornaDados(Consultas.getTopPrioridades());
		else
			return null;
	}

	public static String retornaDados(ArrayList<GraficoPadrao> array) {
		String str = "";
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				str += "data[" + i + "] = {";
				str += "label : \"" + array.get(i).getNome() + "\",";
				str += "data : " + array.get(i).getQnt() + "};\n";
			}
		}
		return str;

	}
}