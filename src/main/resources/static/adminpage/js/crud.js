//ripped from https://www.tutorialrepublic.com/codelab.php?topic=bootstrap&file=table-with-add-and-delete-row-feature

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
    //hide the add button to maintain display: inline-block
    $('#servers_modal table.table td .add').toggle(false);
    //this line could cause problems if the modal doesnt contain any existing entries
    var actions = $("#servers_modal table td:last-child").html();
    // Append table with add row form on add new button click
    $(".add-new").click(function(){
        $(this).attr("disabled", "disabled");
        var index = $("#servers_modal table tbody tr:last-child").index();
        var row = '<tr id="server_0">' +
            '<td><input type="text" class="form-control" name="server_label" id="server_label"></td>' +
            '<td><input type="text" class="form-control" name="server_hostname" id="server_hostname"></td>' +
            '<td><input type="text" class="form-control" name="server_streamport" id="server_streamport"></td>' +
            '<td><input type="text" class="form-control" name="server_apiport" id="server_apiport"></td>' +
            '<td><input type="text" class="form-control" name="server_user" id="server_user"></td>' +
            '<td><input type="text" class="form-control" name="server_pass" id="server_pass"></td>' +
            '<td class="crud">' + actions + '</td>' +
            '</tr>';
        $("#servers_modal table").append(row);
        $("#servers_modal table tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });
    // Add row on add button click
    $(document).on("click", ".add", function(){
        var empty = false;
        //var input = $(this).closest("tr").find('input[type="text"]');
        var input = $(this).closest("tr").find('input[type="text"], input[type="password"]');
        input.each(function(){
            if(!$(this).val()){
                $(this).addClass("error");
                empty = true;
            } else{
                $(this).removeClass("error");
            }
        });
        $(this).closest("tr").find(".error").first().focus();
        if(!empty){
            input.each(function () {
                if ($(this).is(".pw")) {

                    var asterisks = $(this).val().split('').map(function () {
                        return "*";
                    });
                    $(this).parent("td").text(asterisks.join("")).append('<input type="hidden" value="' + $(this).val() + '"/>');
                } else {
                    $(this).parent("td").html($(this).val());
                }

            });
            $(this).closest("tr").find(".add, .edit").toggle();
            $(".add-new").removeAttr("disabled");
        }
    });
    // Edit row on edit button click
    $(document).on("click", ".edit", function(){
        $(this).closest("tr").find("td:not(:last-child)").each(function () {
            if ($(this).is(".pw")) {
                // $(this).html('<input type="password" class="form-control pw" value="' + $(this).find('input[type="hidden"]').val() + '">');
            } else {
                $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
            }

        });
        $(this).closest("tr").find(".add, .edit").toggle();
        $(".add-new").attr("disabled", "disabled");
    });
    // Delete row on delete button click
    $(document).on("click", ".delete", function () {
        if (!$(".add-new").prop("disabled")) {
            var delable = $("#servers_table").data("delete");
            delable += $(this).closest("tr").attr("id").replace("server_", "") + "¬";
            $("#servers_table").data("delete", delable);
        }
        $(this).closest("tr").remove();
        $(".add-new").removeAttr("disabled");
    });
});