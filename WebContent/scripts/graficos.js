function pieHover(event, pos, obj) {
	if (!obj)
		return;
	percent = parseFloat(obj.series.percent).toFixed(2);
	str = obj.series.data+"";
	quantidade = str.substring(2);
	$("#dados")
			.html(
					'<div style="margin: 5px 5px 5px 5px;"><span style="text-shadow: white 0.1em 0.1em; color: '
							+ obj.series.color
							+ '"><strong>Nome</strong>: '
							+ obj.series.label
							+ '<br/><br/> <strong>Porcentagem</strong>: '
							+ percent
							+ '%<br/><br/> <strong>Quantidade</strong>: '
							+ quantidade
							+ '	</span></div>');
}
