$(document).ready(function () {
    findAllBigCategory(1);
    findAllPageBigNumber();
});

//==================================page=============================.unbind('click')
function pageBigCategory(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" name="${i}" ">${i}</a></li> `;
    }
    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
}

function findAllPageBigNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/category/showBig/size",
        success: function (size) {
            console.log(size);
            pageBigCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                findAllBigCategory(page);
            });
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


//============ Get All Big Category ========================
function findAllBigCategory(page) {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/category/showBig?page=" + page,
        success: function (bigCategories) {
            const listSize = Object.keys(bigCategories).length;
            if (bigCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';

                $("#column-big-category").html(
                    "<td> Id</td>" +
                    "<td> Name</td>" +
                    "<td> Action</td>"
                );
                var url = window.location.origin;
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                        <tr>
                        <td> ${bigCategory.id} </td>
                        <td> ${bigCategory.name} </td>
                        <td>
                                 <a href="${url}/admin/update-category/${bigCategory.id}" name="${bigCategory.id}"  class="update-big-category" style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                                 <span name="${bigCategory.id}" class="delete-big-category" style="cursor: pointer;color: red">delete</span>&nbsp;

                        </td>
                        </tr>
                    `;
                });
                $("#row-big-category").html(contentRow);
                //============ delete =============
                deleteBigCategory();
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

//============ Delete Big Category ========================
function deleteBigCategory() {

    $('.delete-big-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/category/delete-big?id=" + id,
            timeout: 30000,
            success: function () {
                alert('SUCCESS');
                return;
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
    });
}



