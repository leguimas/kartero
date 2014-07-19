var com = { qmino : { miredot : {}}};
com.qmino.miredot.restApiSource = {"licenceType":"FREE","projectVersion":"1.0.1","allowUsageTracking":true,"jsonDocHidden":true,"licenceHash":"-4829967114119291759","licenceErrorMessage":null,"miredotVersion":"1.4","validLicence":true,"projectTitle":"Kartero 1.0.1","projectName":"Kartero - Base de CEP dos Correios","dateOfGeneration":"2014-07-19 20:45:22","jsonDocEnabled":false};
com.qmino.miredot.restApiSource.tos = {

};

com.qmino.miredot.restApiSource.enums = {

};
com.qmino.miredot.restApiSource.interfaces = [
	{
		"beschrijving": "Dado um CEP, retorna as informa&ccedil;&otilde;es do endere&ccedil;o atre&ccedil;ado ao CEP em quest&atilde;o.",
		"url": "/cep/{cep}",
		"http": "GET",
		"title": "Consulta CEP",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json;charset=UTF-8"],
		"roles": [],
		"output": {"typeValue": { "type": "simple", "typeValue": "javax.ws.rs.core.Response" }, "comment": "Dados do endereço de um CEP."},
		"statusCodes": [
                { "httpCode": 200, "comment": "O CEP informado foi encontrado."},
                { "httpCode": 404, "comment": "Foi informado um CEP n&atilde;o cadastrado na base de dados."}
            ],
		"hash": "-103106771",
		"inputs": {
                "PATH": [{"name": "cep", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "O CEP a ser procurado. Voc&ecirc; pode informar o CEP em qualquer formato. No processamento, ser&atilde;o processados utilizados apenas os d&iacute;gitos num&eacute;ricos.", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	}];
com.qmino.miredot.projectWarnings = [
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	}];
com.qmino.miredot.processErrors  = [
];

