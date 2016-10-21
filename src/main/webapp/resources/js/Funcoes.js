/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarSenha(input) {
    if (input.value !== document.getElementById("senha").value) {
        input.setCustomValidity("As senhas não são iguais");
    } else {
        input.setCustomValidity("");
    }
}
//função para a criação de máscaras nos campos
function formata_mascara(campo_passado, macscara)
{
    var campo = campo_passado.value.length;
    var saida = mascara.substring(0, 1);
    var texto = mascara.substring(campo);
    if (texto.substring(0, 1) != saida)
    {
        campo_passado.value += texto.substring(0, 1);
    }
}
function Numero(e)
{
    var tecla;
    navegador = /msie/i.test(navigator.userAgent);
    if (navegador)
        tecla = event.keyCode;
    else
        tecla = e.which;
    if (tecla > 47 && tecla < 58) // numeros de 0 a 9
        return true;
    else
    {
        if (tecla != 8) // backspace
            return false;
        else
            return true;
    }
}
function moeda(z) {
    v = z.value;
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    /* v = v.replace(/[0-9]{12}/, "inválido")   //limita pra máximo 999.999.999,99
     v = v.replace(/(\d{1})(\d{8})$/, "$1.$2")  //coloca ponto antes dos últimos 8 digitos
     v = v.replace(/(\d{1})(\d{5})$/, "$1.$2")  //coloca ponto antes dos últimos 5 digitos*/
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2");	//coloca virgula antes dos últimos 2 digitos
    z.value = v;
}
function moeda2(z) {
    v = z.value;
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "inválido");   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{7})$/, "$1.$2");  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{4})$/, "$1.$2");  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,1})$/, "$1,$2");	//coloca virgula antes dos últimos 2 digitos
    z.value = v;
}
function moedav(z) {
    v = z.value;
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "inválido");   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2");  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2");  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2");	//coloca virgula antes dos últimos 2 digitos
    z.value = v;
}
function moeda3(z) {
    v = z.value;
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "inválido");   //limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{7})$/, "$1.$2");  //coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{4})$/, "$1.$2");  //coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,1})$/, "$1,$2");	//coloca virgula antes dos últimos 2 digitos
    z.value = v;
}

function pegaAlgo(z) {
    v = z.value;
    document.frmAletraFore.valorReal.value = v;
}

function pegaAlgoPercent(z) {
    v = z.value;
    a = document.frmAletraFore.lblfore.value;
    a = replaceAll(a, ",", ".");
    v = replaceAll(v, ",", ".");
    v = converteNumero(v);
    a = converteNumero(a);
    x = ((a * v) / 100) + a;
    document.frmAletraFore.valorReal.value = x;
}

function replaceAll(string, token, newtoken) {
    while (string.indexOf(token) != -1) {
        string = string.replace(token, newtoken);
    }
    return string;
}
function apenasNumeros(string)
{
    var numsStr = string.replace(/[^0-9]/g, '');
    return parseInt(numsStr);
}
function trim(vlr) {

    while (vlr.indexOf(" ") != - 1)
        vlr = vlr.replace(" ", "");
}
String.prototype.formatMoney = function () {
    var v = this;
    if (v.indexOf('.') === -1) {
        v = v.replace(/([\d]+)/, "$1,00");
    }

    v = v.replace(/([\d]+)\.([\d]{1})$/, "$1,$20");
    v = v.replace(/([\d]+)\.([\d]{2})$/, "$1,$2");
    v = v.replace(/([\d]+)([\d]{3}),([\d]{2})$/, "$1.$2,$3");
    return v;
};
function id(el) {
    return document.getElementById(el);
}
function getMoney(el) {
    var money = id(el).value.replace(',', '.');
    return parseFloat(money) * 100;
}
function converteNumero(x) {
    if (x == "")
        return 0;
    else
        return parseFloat(x);
}
function converteInteiro(x) {
    if (x == "")
        return 0;
    else
        return parseInt(x);
}

function para() {
    if (event.keyCode == "13") {
        event.keyCode = 9;
        return false;
    }
    return false;
}

function enterPorTab() {
    if (event.keyCode == 13)
        event.keyCode = 9;
}

function contaPontos(x) {
    var procura = '.';
    var string = x;
    var resultado = string.match(procura);
    var result = resultado.length;
    return result;
}
function msg() {
    $(function msg() {
        var $conteudo = $('#conteudo').width(); // largura total	
        var $banner = $('#banner'); // objeto banner
        var $tempo = 8; // milisegundos
        var $intervalo;
        // evento click
        $(".fechar").click(function (event) {
            event.preventDefault();
            fechar(); // chamada a função
        });
        // funcao que fechará o banner
        function fechar() {
            $("#banner").hide();
        }

        // funcao para contagem
        function contador() {
            $intervalo = window.setInterval(function () {
                var tempoContagem = $("#contador").html();
                var atualizaContagem = eval(tempoContagem) - eval(1);
                $("#contador").html(atualizaContagem);
                // chegando em zero o contador é parado
                if (atualizaContagem == 0) {
                    pararContagem();
                }
            }, 1000);
        }

        // funcao para limpar o contador 
        function pararContagem() {
            window.clearInterval($intervalo);
        }

        // deslocamento do banner
        //$banner.animate({ left: ($conteudo /4)}, 900).show();
        $banner.show();
        // chamada da funcao que fará a contagem
        contador();
        // setando o tempo de execução do banner
        //setTimeout(fechar, $tempo*1000);
    });
}
$(function () {
    $('.excluir').click(function () {
        var delartigo = $(this).attr('href');
        /** Abre a modal de Confirmação*/
        $('.modal').fadeIn('slow', function () {
            $('.modal-container').fadeIn('slow');
            $('.sim').attr('href', delartigo);
        })
        return  false;
    })
    /** Corfimando a Exclusão */
    $('.sim').click(function () {
        var delartigo = $(this).attr('href');
        $('.modal-container').fadeOut('slow', function () {
            $('.modal').fadeOut('slow', function () {
                $(location).attr('href', delartigo);
            });
        });
        return false;
    });
    /** Fecha a modal/ corfima~ção negada*/
    $('.nao').click(function () {
        $('.modal-container').fadeOut('slow', function () {
            $('.modal').fadeOut('slow');
        });
        return false;
    });
});

function escondeClassesBt(classeAEsconder, bt) {
    if ($("." + classeAEsconder).is(':visible')) {
        $("." + classeAEsconder).fadeOut('slow', function () {
            $('#' + bt).removeClass('bg1').addClass('bg2');
        });
    } else {
        $("." + classeAEsconder).fadeIn('slow', function () {
            $('#' + bt).removeClass('bg2').addClass('bg1');
        });
    }

    //$("." + classeAEsconder).css("display", "none");
}
function ocultarClasseCk(classeAEsconder, ck) {

    if ($("." + classeAEsconder).is(':visible')) {
        $("." + classeAEsconder).fadeOut('slow');
        for (var i = 1; i <= 12; i++) {
            var colspan = $('#capa-mes' + i).attr('colspan');
            $('#capa-mes' + i).attr('colspan', colspan - 1);
        }
        $("#" + ck).removeAttr("checked");
    } else {
        $("." + classeAEsconder).fadeIn('slow');
        for (var i = 1; i <= 12; i++) {
            var colspan = $('#capa-mes' + i).attr('colspan');
            var cols = converteNumero(colspan) + 1;
            $('#capa-mes' + i).attr('colspan', cols);
        }
        $("#" + ck).Attr("checked", "true");
    }

}

function escondeClasses(classeAEsconder) {
    if ($("." + classeAEsconder).is(':visible')) {
        $("." + classeAEsconder).fadeOut('slow', function () {
        });
        return false;
    } else {
        $("." + classeAEsconder).fadeIn('slow', function () {
        });
    }
}

function escondeClassesAlteraButom(classeAEsconder) {
    if ($("." + classeAEsconder).is(':visible')) {
        $("." + classeAEsconder).fadeOut('slow', function () {
        });
        $("#icone-botao").removeClass('glyphicon-minus');
        $("#icone-botao").addClass('glyphicon-plus');
        return false;
    } else {
        $("." + classeAEsconder).fadeIn('slow', function () {
        });
        $("#icone-botao").removeClass('glyphicon-plus');
        $("#icone-botao").addClass('glyphicon-minus');
    }
}

function abrirModal() {
    $('#modal-form').fadeIn('slow');
}
function trocaCamposPesquisaCliente(clase) {
    var tipo = clase;
    if (tipo == "data") {
        $('.filtrotext').fadeOut('slow', function () {
            $('.pesData').fadeIn('slow');
        });
    } else {
        $('.pesData').fadeOut('slow', function () {
            $('.filtrotext').fadeIn('slow');
        });
    }
}

jQuery(document).ready(function ($) {
    // Chamada da funcao upperText(); ao carregar a pagina
    upperText();
    // Funcao que faz o texto ficar em uppercase
    function upperText() {
// Para tratar o colar
        $(".maiusculo").bind('paste', function (e) {
            var el = $(this);
            setTimeout(function () {
                var text = $(el).val();
                el.val(text.toUpperCase());
            }, 100);
        });

// Para tratar quando é digitado
        $(".maiusculo").keypress(function () {
            var el = $(this);
            setTimeout(function () {
                var text = $(el).val();
                el.val(text.toUpperCase());
            }, 100);
        });
    }
});
$(function () {
    $('.loadCartao').click(function () {
        $('.carregando').fadeIn('slow');
    });
});

$(function () {
    $('.loadCartao2').click(function () {
        $('.carregando2').fadeIn('slow');
    });
});

$(function () {
    /** Corfimando a Exclusão */
    $('.sim').click(function () {
        var delartigo = $(this).attr('href');
        $('.modal-container-cartao').fadeOut('slow', function () {
            $('.modal-cartao').fadeOut('slow', function () {
                $(location).attr('href', delartigo);
            });
        });
        return false;
    });
    /** Fecha a modal/ corfima~ção negada*/
    $('.nao').click(function () {
        $('.modal-container-cartao').fadeOut('slow', function () {
            $('.modal-cartao').fadeOut('slow');
            history.back();
        });
        return false;
    });
});

/* $(function () {
 $('.sv-conciliacao').click(function () {
 if ($("#ck-dpc").is(":checked")) {
 $("#lbl-msgdpc").text('');
 } else {
 $("#lbl-msgdpc").text('Voce deve selecionar o movimento da loja que deseja conciliar.');
 //alert('Voce deve selecionar o movimento da loja que deseja conciliar.');
 return false;
 }
 if ($("#ck-dc").is(":checked")) {
 $("#lbl-msgdc").text('');
 } else {
 $("#lbl-msgdc").text('Voce deve selecionar o movimento da operadora que deseja conciliar.');
 // alert('Voce deve selecionar o movimento da operadora que deseja conciliar.');
 return false;
 }
 });
 }); */
/** funçoes do fechamento de caixa*/
$(function () {
    $('.adicionaitem').click(function () {
        $('.tritemadicional').fadeIn('slow', function () {
            $('#additem').addClass('hide');
            $('#removeitem').removeClass('hide');
        });

        return false;
    });
    $('.removeitem').click(function () {
        $('.tritemadicional').fadeOut('slow', function () {
            $('#removeitem').addClass('hide');
            $('#additem').removeClass('hide');
        });
        return false;
    });
});
$(function () {
    $('.adicionaobs').click(function () {
        $('.trobs').fadeIn('slow', function () {
            $('#addobs').addClass('hide');
            $('#removeobs').removeClass('hide');
        });

        return false;
    });
    $('.removeobs').click(function () {
        $('.trobs').fadeOut('slow', function () {
            $('#removeobs').addClass('hide');
            $('#addobs').removeClass('hide');
        });
        return false;
    });
});

/**Fim das funçoes do fechamento */


$(function () {
    $('.naoload').click(function () {
        $('.modal-cartao').fadeOut('slow', function () {
            $('.modal-container-cartao').fadeOut('slow');
        });
    });
});


/** Mensagem do Cupon Detalhado **/
$(function () {
    /** Corfimando a Exclusão */
    $('.sim-cp').click(function () {
        var delartigo = $(this).attr('href');
        $('.modal-container-cp').fadeOut('slow', function () {
            $('.modal-cp').fadeOut('slow', function () {
                $(location).attr('href', delartigo);
            });
        });
        return false;
    });
    /** Fecha a modal/ corfima~ção negada*/
    $('.nao-cp').click(function () {
        $('.modal-container-cp').fadeOut('slow', function () {
            $('.modal-cp').fadeOut('slow');
        });
        return false;
    });
});


/** Fim da Mensagem do Cupon detalhado **/




$(function () {
    if ($('#tipopesdata').is(':checked')) {
        $('#frmfiltrodata').fadeIn('slow');
    } else if ($('#tipopesnome').is(':checked')) {
        $('#frmfiltronome').fadeIn('slow');
    } else if ($('#tipopesvalor').is(':checked')) {
        $('#frmfiltrovalor').fadeIn('slow');
    }
    $('#tipopesdata').click(function () {
        $('#frmfiltronome').fadeOut('slow');
        $('#frmfiltrovalor').fadeOut('slow');
        $('#frmfiltrodata').fadeIn('slow');
    });
    $('#tipopesnome').click(function () {
        $('#frmfiltrodata').fadeOut('slow');
        $('#frmfiltrovalor').fadeOut('slow');
        $('#frmfiltronome').fadeIn('slow');
        $('#pes').attr("value", "");
    });
    $('#tipopesvalor').click(function () {
        $('#frmfiltronome').fadeOut('slow');
        $('#frmfiltrodata').fadeOut('slow');
        $('#frmfiltrovalor').fadeIn('slow');
        $('#pesv').attr("value", "");
    });
});



$(function () {
    $('#ck-rdz').click(function () {
        $('#div-cf').fadeOut('slow', function () {
            $('#div-rdz').fadeIn('slow');
        });
    });
    $('#ck-cf').click(function () {
        $('#div-rdz').fadeOut('slow', function () {
            $('#div-cf').fadeIn('slow');
        });
    });

});


$(function () {
    $('#text-search').bind('keyup change', function (ev) {
        // pull in the new value
        var searchTerm = $(this).val();

        // remove any old highlighted terms
        $('body').removeHighlight();

        // disable highlighting if empty
        if (searchTerm) {
            // highlight the new term
            $('body').highlight(searchTerm);
        }
    });
});



$(function () {
    $('#emp').change(function () {
        document.getElementById('frm_confcaixa').submit();
    });
});
$(function () {
    $('#emp_nota').change(function () {
        document.getElementById('frm_confcaixa_nota').submit();
    });
});

$(function () {
    $('#tabelanota tbody tr').hover(
            function () {

            },
            function () {

            }
    );
});

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}
