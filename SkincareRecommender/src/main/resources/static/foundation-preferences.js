// var token = $('#_csrf').attr('content');
// var header = $('#_csrf_header').attr('content');
$(document).ready(function () {

    $('#select-brand').on("click", function (event) {
        
        var brandid = $('#brandid').val();
        $.ajax({
            type: 'GET',
            url: `/chooseBrand/${brandid}`,
            // beforeSend: function (xhr) {
            //     xhr.setRequestHeader(header, token);
            // },
            headers: {
                'Accept': 'application/json'
            }
        }).done(function (data) {
            var p = data;

            var row = $('#append-product');
            var html = '<div class="col-md-6" id="product"><p>Select your favorite foundation</p><select class="form-control" id="itemnumber" name="product.productid">';
            for (var i = 0; i < p.length; i++) {
                html += `<option value="${p[i].itemnumber}">${p[i].productname}</option>`;
            }
            html += '</select></div>';
            html += '<div id="show-preferences" class="col-md-6"><button type="button" onclick="showPreferences()" class="btn btn-dark">Continue</button></div>'
            row.append(html);
        });


    });



});

function showPreferences() {

    $('#concerns').show();

}

function calculate() {
    var product = $('#itemnumber').val();
    var skintype = $('#skintype').val();
    var coverage = $('#coverage').val();
    var finish = $('#finish').val();
    var selected = [];
    $('#attribute input:checked').each(function () {
        selected.push($(this).attr('value'));
    });
    var attributeid = "[";
    for (var i = 0; i < selected.length; i++) {
        if (i == selected.length - 1) {
            attributeid += `${selected[i]}`;
        } else {
            attributeid += `${selected[i]},`;
        }
    }
    attributeid += "]";
    // $(jQuery.parseJSON(JSON.stringify(selected))).each(function(){
    //     var attributeid = this.attributeid;
    // });
    //$(jQuery.parseJSON(JSON.stringify(attributeid)))
    console.log(attributeid);
    var obj = JSON.parse(attributeid);
    var crueltyfree = $('#crueltyfree').is(":checked");


    $.ajax({
        type: "POST",
        url: "/calculate",
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader(header, token);
        // },
        data: JSON.stringify({
            "product": {
                "itemnumber": product
            },
            "skintype": {
                "skintypeid": skintype
            },
            "coverage": {
                "coverageid": coverage
            },
            "finish": {
                "finishid": finish
            },
            "a": obj,

            "crueltyfree": crueltyfree
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    }).done(function (data) {
        window.location.replace(`/foundation-preferences/${data}`);
    })
}