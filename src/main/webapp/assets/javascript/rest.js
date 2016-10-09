
window.addEventListener("load", function ()
{

    PersonsRefresh();
    CompaniesRefresh();

    $("#addPersonBtn").click(function ()
    {
        var rows = '<tr class="tmpPersonRow"><td>' + '</td><td>' + '</td><td>' + '</td><td>' + '<input type="text" name="tmpfirstname" required />' + '</td><td>' + '<input type="text" name="tmplastname" required />' + '</td><td>' + '<input type="text" name="tmpstreet" required />' + '</td><td>' + '<input type="text" name="tmpadditionalinfo" required />' + '</td><td>' + '<input type="number" name="tmpzipcode" min="500" required />' + '</td><td>' + '<input type="text" name="tmpcity" required />' + '</td></tr>';
        $('#TablePersons').append(rows);
        $("#pSubmit").remove();
        $("#pCancel").remove();
        $('#personBtns').append('<button type="submit" id="pSubmit" value="" onclick="TablePersonAdd();"><img src="assets/images/applyicon.png" class="button-table1" alt="applyicon.png"/> Save</button>');
        $('#personBtns').append(" ");
        $('#personBtns').append('<button type="submit" id="pCancel" value="" onclick="TablePersonRemove();"><img src="assets/images/cancelicon.png" class="button-table1" alt="cancelicon.png"/> Cancel</button>');

    });

    $("#addCompanyBtn").click(function ()
    {
        var rows = '<tr class="tmpCompanyRow"><td>' + '</td><td>' + '</td><td>' + '<input type="text" name="company" required />' + '</td><td>' + '<input type="text" name="description" required />' + '</td><td>' + '<input type="number" name="marketvalue" required />' + '</td><td>' + '<input type="number" name="employees" min="1" required />' + '</td><td>' + '<input type="text" name="city" required />' + '</td></tr>';
        $('#TableCompanies').append(rows);
        $("#cSubmit").remove();
        $("#cCancel").remove();
        $('#companyBtns').append('<button type="submit" id="cSubmit" value="" onclick="TableCompanyAdd();"><img src="assets/images/applyicon.png" class="button-table1" alt="applyicon.png"/> Save</button>');
        $('#companyBtns').append(" ");
        $('#companyBtns').append('<button type="submit" id="cCancel" value="" onclick="TableCompanyRemove();"><img src="assets/images/cancelicon.png" class="button-table1" alt="cancelicon.png"/> Cancel</button>');

    });


    $("#deletePersonBtn").click(function ()
    {

        var length = "";
        var sThisVal = "";
        $('input:checkbox.checkmark').each(function () {

            sThisVal = (this.checked ? $(this).val() : "");
            if (sThisVal > 0)
            {
                length = length + 1;
            }
        });

        var r = "";
        if (length > 1)
        {
            r = confirm("Are you sure you want to delete these rows?");
        } else
        {
            r = confirm("Are you sure you want to delete this row?");
        }

        if (r === true)
        {
            $('input:checkbox.checkmark').each(function () {

                sThisVal = (this.checked ? $(this).val() : "");
                if (sThisVal > 1)
                {
                    $.ajax({
                        url: "api/person/delete/" + sThisVal,
                        type: "DELETE",
                        data: "",
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function ()
                        {
                            PersonsRefresh();
                            location.reload(); // this is anoying. if we don't do this, we can't search the table.
                        }
                    });
                }

            });
        }

        
    });

});

//JQuery / Javascript

function PersonsRefresh()
{
    $.getJSON("api/person/complete/", function (persons)
    {

        $('#TablePersons thead').remove();
        $('#TablePersons th').remove();
        $('#TablePersons tr').remove();
        $('#TablePersons tbody').remove();
        $('#TablePersons td').remove();
        var rows = "";

        rows += '<thead><tr><th>' + "EDIT"
                + '</th><th>' + "SELECT"
                + '</th><th>' + "ID"
                + '</th><th>' + "First name"
                + '</th><th>' + "Last name"
                + '</th><th>' + "Street"
                + '</th><th>' + "HouseNr"
                + '</th><th>' + "Zipcode"
                + '</th><th>' + "City" + '</th></tr></thead><tbody>';

        $.each(persons, function (i, person)
        {
            rows += '<tr class="pClick" ><td><button type="submit" id="pEdit' + person.id + '" value="' + person.id + '" onclick="edit_row(' + person.id + ')"><img src="assets/images/editicon.png" class="button-table2" alt="editicon.png"/> '
                    + '</td><td><input type="checkbox" class="checkmark" name="rowCheck" value="' + person.id + '" > '
                    + '</td><td class="pTable">' + person.id + '</td><td id="firstname' + person.id + '">' + person.firstname
                    + '</td><td class="pTable" id="lastname' + person.id + '">' + person.lastname
                    + '</td><td class="pTable" id="street' + person.id + '">' + person.address.street
                    + '</td><td class="pTable" id="additionalinfo' + person.id + '">' + person.address.additionalinfo
                    + '</td><td class="pTable" id="zip' + person.id + '">' + person.zip
                    + '</td><td class="pTable" id="city' + person.id + '">' + person.city + '</td></tr>';
        });
        rows += $('</tbody>');
        $('#TablePersons').append(rows);
        $('#TablePersons').DataTable();
    });

}



function CompaniesRefresh()
{
    $.getJSON("api/company/complete/", function (companies)
    {
        $('#TableCompanies tr').remove();
        var rows = "";
        rows += '<tr><th>' + "EDIT" + '</th><th>' + "SELECT" + '</th><td>' + "ID" + '</td><td>' + "First name" + '</td><td>' + "Last name" + '</td><td>' + "Street" + '</td><td>' + "Zipcode" + '</td><td>' + "City" + '</td></tr>';
        $.each(companies, function (i, company)
        {
            rows += '<tr><td><button type="submit" name="rowCheck" value="person.id" ' + '</td><td><input type="checkbox" class="checkmarkCompany" name="rowCheck" value="person.id" /> ' + '</td><td>' + company.id + '</td><td>' + company.firstname + '</td><td>' + company.lastname + '</td><td>' + company.address + '</td><td>' + company.zip + '</td><td>' + company.city + '</td></tr>';
        });

        $('#TableCompanies').append(rows);
    });
}

function CompanyDelete(id)
{
    $.ajax({
        url: "api/company/delete/" + id,
        type: "DELETE",
        data: "",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function ()
        {
            CompaniesRefresh();
        }
    });

}


function TablePersonAdd()
{
    $('.tmpPersonRow').each(function (i, obj) {

        $firstname = $(this).find('input[name*="tmpfirstname"]'),
                $lastname = $(this).find('input[name*="tmplastname"]'),
                $street = $(this).find('input[name*="tmpstreet"]'),
                $houseNr = $(this).find('input[name*="tmpadditionalinfo"]'),
                $zipcode = $(this).find('input[name*="tmpzipcode"]'),
                $city = $(this).find('input[name*="tmpcity"]');

        if ($firstname.val() === "" || $lastname.val() === "" || $street.val() === "" || $houseNr.val() === "" || $zipcode.val() === "" || $city.val() === "")
        {
            alert("All fields must be filled!");
        } else
        {
            var person = '{ firstname: ' + $firstname.val() + ', lastname: ' + $lastname.val()
                    + ',email: ' + 'an@email.dk'
                    + ',address:' + '{'
                    + 'additionalinfo: ' + $houseNr.val()
                    + ',street: ' + $street.val()
                    + '}'
                    + ', city: ' + $city.val()
                    + ', zip: ' + $zipcode.val()
                    + ',phonenumbers:' + '[]'
                    + ',phonenumbers.number: ' + "12345678"
                    + ',phonenumbers.description: ' + "asasdasd"
                    + ',hobbies:' + '[]'
                    + ',hobbies.description: ' + "thisHobby"
                    + ',hobbies.name: ' + "aHobby" + '}';
            $.ajax(
                    {
                        url: "api/person/complete/add",
                        type: "POST",
                        data: person,
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function ()
                        {

                            TablePersonRemove();

                        },
                        error: function (error)
                        {
                            alert(error) + "addPerson";
                        }
                    });
        }
    });


}
function TablePersonEdit()
{

    $('.tmpEditPersonRow').each(function (i, obj) {
        $id = $(this).find('input[name*="tmpid"]'),
                $firstname = $(this).find('input[name*="tmpfirstname"]'),
                $lastname = $(this).find('input[name*="tmplastname"]'),
                $street = $(this).find('input[name*="tmpstreet"]'),
                $houseNr = $(this).find('input[name*="tmpadditionalinfo"]'),
                $zipcode = $(this).find('input[name*="tmpzipcode"]'),
                $city = $(this).find('input[name*="tmpcity"]');

        if ($firstname.val() === "" || $lastname.val() === "" || $street.val() === "" || $houseNr.val() === "" || $zipcode.val() === "" || $city.val() === "")
        {
            alert("All fields must be filled!");
        } else
        {

            var person = '{ id: ' + $id.val()
                    + ',firstname: ' + $firstname.val() + ', lastname: ' + $lastname.val()
                    + ',email: ' + 'an@email.dk'
                    + ',address:' + '{'
                    + 'additionalinfo: ' + $houseNr.val()
                    + ',street: ' + $street.val()
                    + '}'
                    + ', city: ' + $city.val()
                    + ', zip: ' + $zipcode.val()
                    + ',phonenumbers:' + '[]'
                    + ',phonenumbers.number: ' + "12345678"
                    + ',phonenumbers.description: ' + "asasdasd"
                    + ',hobbies:' + '[]'
                    + ',hobbies.description: ' + "thisHobby"
                    + ',hobbies.name: ' + "aHobby" + '}';

            $.ajax({
                url: "api/person/editperson",
                type: "PUT",
                data: person,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function () {
                    TableEditPersonRemove();
                },
                error: function (error)
                {
                    alert(error);
                }
            });
        }
    });


}



function edit_row(no)
{
    var rows = '<tr class="tmpEditPersonRow"><td>' + '<button type="submit" id="pEditSave" value="" onclick="TablePersonEdit();"><img src="assets/images/applyicon.png" class="button-table3" alt="applyicon.png"/> Save</button>'
            + '</td><td>' + ' <button type="submit" id="pEditCancel" value="" onclick="TableEditPersonRemove();"><img src="assets/images/cancelicon.png" class="button-table3" alt="cancelicon.png"/> Cancel</button> '
            + '</td><td>'
            + '</td><td>' + '<input type="text" name="tmpfirstname" required />' + '</td><td>'
            + '<input type="text" name="tmplastname" required />' + '</td><td>'
            + '<input type="text" name="tmpstreet" required />' + '</td><td>'
            + '<input type="text" name="tmpadditionalinfo" required />' + '</td><td>'
            + '<input type="number" name="tmpzipcode" min="500" required />' + '</td><td>'
            + '<input type="text" name="tmpcity" required />'
            + '<input type="hidden" name="tmpid" value="' + no + '" required />'
            + '</td></tr>';
    $("td#firstname" + no).parent().replaceWith(rows);

}


function TablePersonRemove()
{
    location.reload(); // this is anoying. if we don't do this, we can't search the table.
    $("#pSubmit").remove();
    $("#pCancel").remove();
    PersonsRefresh();
}

function TableEditPersonRemove()
{
    location.reload(); // this is anoying. if we don't do this, we can't search the table.
    $("#pEditSave").remove();
    $("#pEditCancel").remove();
    PersonsRefresh();
}

function TableCompanyRemove()
{
    $("#cSubmit").remove();
    $("#cCancel").remove();
    CompaniesRefresh();
}


function TableCompanyAdd()
{
    $('.tmpCompanyRow').each(function (i, obj)
    {

        $firstname = $(this).find('input[name*="firstname"]'),
                $lastname = $(this).find('input[name*="lastname"]'),
                $street = $(this).find('input[name*="street"]'),
                $houseNr = $(this).find('input[name*="additionalinfo"]'),
                $zipcode = $(this).find('input[name*="zipcode"]'),
                $city = $(this).find('input[name*="city"]');

        var person = '{ firstname: ' + $firstname.val() + ', lastname: ' + $lastname.val()
                + ',email: ' + 'an@email.dk'
                + ',address:' + '{'
                + 'additionalinfo: ' + $houseNr.val()
                + ',street: ' + $street.val()
                + '}'
                + ', city: ' + $city.val()
                + ', zip: ' + $zipcode.val()
                + ',phonenumbers:' + '[]'
                + ',phonenumbers.number: ' + "12345678"
                + ',phonenumbers.description: ' + "asasdasd"
                + ',hobbies:' + '[]'
                + ',hobbies.description: ' + "thisHobby"
                + ',hobbies.name: ' + "aHobby" + '}';

        $.ajax({
            url: "api/person/complete/add",
            type: "POST",
            data: person,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function () {}
        });

    });

    CompaniesRefresh();
}



