$(document).ready(function () {
    clickBtnPartnerChangeSubmit();
});

//============ CREATE PARTNER ========================

function createProduct() {

    $("#btn-create-partner").prop("disabled", true);

    $('#btn-create-partner').click(function () {

        const nameProduct = $('#name-partner-value').val();
        const originCost = $('#present-partner-value').val();
        const imgUrl = $('#image-partner-value').val();
        const partner = {
            "name": nameProduct,
            "present": originCost,
            "imgUrl": imgUrl,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/admin/partner",
            data: JSON.stringify(partner),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $('#btn-create-partner').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("CREATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $('#btn-create-partner').prop("disabled", true);
            }
        })
    });
}

// ============ FIND PARTNER BY ID ===================

function findPartnerById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/api/v1/public/partner/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updatePartner(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('jqXHR:');
            console.log(jqXHR);
            console.log('textStatus:');
            console.log(textStatus);
            console.log('errorThrown:');
            console.log(errorThrown);
        }
    });
}

//============ UPDATE PARTNER ========================
function updatePartner(data) {

    $('#name-partner-value').val(data.name);
    $('#present-partner-value').val(data.present);


    $('#btn-create-partner').click(function () {
        data.name = $('#name-partner-value').val();
        data.present = $('#present-partner-value').val();
        data.imgUrl = $('#image-partner-value').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/partner",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-partner').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("UPDATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $('#btn-create-partner').prop("disabled", true);

            }
        });
    });
}

function clickBtnPartnerChangeSubmit() {
    const urlCreateCategory = window.location.pathname;
    const str = urlCreateCategory.split('/');
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findPartnerById(id)
    } else createProduct();

}

