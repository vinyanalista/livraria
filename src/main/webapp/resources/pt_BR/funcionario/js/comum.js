$.extend($.fn.dataTable.defaults, {
	"oLanguage" : {
		"sProcessing" : "Processando...",
		"sLengthMenu" : "Mostrar _MENU_ registros",
		"sZeroRecords" : "Não foram encontrados resultados",
		"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
		"sInfoEmpty" : "Mostrando de 0 até 0 de 0 registros",
		"sInfoFiltered" : "(filtrado de _MAX_ registros no total)",
		"sInfoPostFix" : "",
		"sSearch" : "Buscar:",
		"sUrl" : "",
		// TODO Traduzir e propor alteração no site do DataTables
		// Sem a configuração abaixo, a tabela não é renderizada
		"oAria" : {
			"sSortAscending" : ": clique para ordenar esta coluna de maneira ascendente",
			"sSortDescending" : ": clique para ordenar esta coluna de maneira descendente"
		},
		"oPaginate" : {
			"sFirst" : "Primeiro",
			"sPrevious" : "Anterior",
			"sNext" : "Próximo",
			"sLast" : "Último"
		}
	}
});

jQuery.validator.addClassRules({
  name: {
    required: true,
    minlength: 2
  },
  zip: {
    required: true,
    digits: true,
    minlength: 5,
    maxlength: 5
  }
});

$(document).ready(function(){
	$('a[data-original-title]').tooltip();

	$(".sortableTable").tablesorter();
});

function formatarMoeda(valor) {
	// http://battisti.wordpress.com/2007/03/08/arredondar-formatando-e-desformatando-valores-em-javascript/
	x = 0;

	if (valor < 0) {
		valor = Math.abs(valor);
		x = 1;
	}
	if (isNaN(valor))
		valor = "0";
	cents = Math.floor((valor * 100 + 0.5) % 100);

	valor = Math.floor((valor * 100 + 0.5) / 100).toString();

	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((valor.length - (1 + i)) / 3); i++)
		valor = valor.substring(0, valor.length - (4 * i + 3)) + '.'
				+ valor.substring(valor.length - (4 * i + 3));
	ret = valor + ',' + cents;
	if (x == 1)
		ret = ' - ' + ret;
	return 'R$ ' + ret;

}