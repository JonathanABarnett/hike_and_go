function checkBillingAddress() {
    if ($("#theSameAsShippingAddress").is(":checked")) {
        $(".billingAddress").prop("disabled", true);
    } else {
        $(".billingAddress").prop("disabled", false);
    }
}

function checkPasswordMatch() {
    var newPassword = $("#newPassword");
    var confirmPassword = $("#confirmPassword");

    if (newPassword == "" && confirmPassword == "") {
        $("#checkPasswordMatch").html("");
        $("#updateUserInfoButton").prop('disabled', false);
    } else {
        if (newPassword != confirmPassword) {
            //$("#checkPasswordMatch").html("Passwords do not match.");
            //$("#updateUserInfoButton").prop('disabled', true);
        } else {
            $("#checkPasswordMatch").html("Passwords  match.");
            // $("#updateUserInfoButton").prop('disabled', false);
        }
    }
}


function checkShippingMethod() {
    if ($("#groundShipping").is(":checked")) {
        $(".premiumShipping").hide();
        $(".freeShipping").show();
    } else if ($("#premiumShipping").is(":checked")) {
        $(".premiumShipping").show();
        $(".freeShipping").hide();
    }
}


$(document).ready(function () {
    $(".cartItemQty").on('change', function () {
        var id = this.id;
        $('#update-item-' + id).css('display', 'inline-block');
    });

    $("#theSameAsShippingAddress").on('click', checkBillingAddress);

    checkShippingMethod();
    $("#groundShipping").on('click', checkShippingMethod);
    $("#premiumShipping").on('click', checkShippingMethod);

    $('#newPassword, #confirmPassword').on('keyup', function () {
        if ($('#newPassword').val() == $('#confirmPassword').val()) {
            $('#checkPasswordMatch').html('Passwords Match!').css('color', 'green');
            $('#updateUserInfoButton').prop('disabled', false);
            console.log('#updateUserInfoButton').prop();
        } else
            $('#checkPasswordMatch').html('Passwords Do Not Match').css('color', 'red');
        $('#updateUserInfoButton').prop('disabled', true);
    });

    $('.delete-product').on('click', function () {
        /*<![CDATA[*/
        var path = /*[[@{/}]]*/'remove';
        /*]]>*/

        var id = $(this).attr('id');

        bootbox.confirm({
            message: "Are you sure you would like to delete this product?",
            buttons: {
                cancel: {
                    label: '<i class="fas fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fas fa-check"></i> Confirm'
                }
            },
            callback: function (confirmed) {
                if (confirmed) {
                    $.post(path, {'id': id}, function (res) {
                        console.log(res);
                        location.reload();
                    });
                }
            }
        });
    });

    $('#deleteSelected').click(function () {
        var idList = $('.checkboxProduct');
        var productIdList = [];
        for (var i = 0; i < idList.length; i++) {
            if (idList[i].checked == true) {
                console.log(idList[i]);
                productIdList.push(idList[i]['id'])
            }
        }

        /*<![CDATA[*/
        var path = /*[[@{/}]]*/'removeList';
        /*]]>*/

        bootbox.confirm({
            message: "Are you sure you would like to delete all selected products?",
            buttons: {
                cancel: {
                    label: '<i class="fas fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fas fa-check"></i> Confirm'
                }
            },
            callback: function (confirmed) {
                console.log("Now here");
                if (confirmed) {
                    $.ajax({
                        type: 'POST',
                        url: path,
                        data: JSON.stringify(productIdList),
                        contentType: "application/json",
                        success: function (res) {
                            console.log(res);
                            location.reload()
                        },
                        error: function (res) {
                            console.log(res);
                            location.reload();
                        }
                    });
                }
            }
        });


    });

});