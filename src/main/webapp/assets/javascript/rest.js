/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



window.addEventListener("load", function ()
{

    PersonsRefresh();
    CompaniesRefresh();


});

//JQuery / Javascript

function PersonsRefresh()
{
    $.getJSON("api/person/complete/", function (persons)
    {
        $('#TablePersons tr').remove();
        var rows = "";

        rows += '<tr><th>' + "SELECT" + '</th><th>' + "ID" + '</th><th>' + "First name" + '</th><th>' + "Last name" + '</th><th>' + "Street" + '</th><th>' + "HouseNr" + '</th><th>' + "Zipcode" + '</th><th>' + "City" + '</th></tr>';
        $.each(persons, function (i, person)
        {
            rows += '<tr><td><input type="checkbox" class="checkmarkPerson" name="rowCheck" value="' + person.id + '" > ' + '</td><td>' + person.id + '</td><td>' + person.firstname + '</td><td>' + person.lastname + '</td><td>' + person.address.street + '</td><td>' + person.address.additionalinfo + '</td><td>' + person.zip + '</td><td>' + person.city + '</td></tr>';
        });
        $('#TablePersons').append(rows);

    });
}

function PersonDelete(id)
{
    var r = confirm("Are you sure you want to delete this row?");
    if (r === true)
    {
        $.ajax({
            url: "api/person/delete/" + id,
            type: "DELETE",
            data: "",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function () {}
        });
    }
}

function CompaniesRefresh()
{
    $.getJSON("api/company/complete/", function (companies)
    {
        $('#TableCompanies tr').remove();
        var rows = "";
        rows += '<tr><th>' + "SELECT" + '</th><td>' + "ID" + '</td><td>' + "First name" + '</td><td>' + "Last name" + '</td><td>' + "Street" + '</td><td>' + "Zipcode" + '</td><td>' + "City" + '</td></tr>';
        $.each(companies, function (i, company)
        {
            rows += '<tr><td><input type="checkbox" class="checkmarkCompany" name="rowCheck" value="person.id" /> ' + '</td><td>' + company.id + '</td><td>' + company.firstname + '</td><td>' + company.lastname + '</td><td>' + company.address + '</td><td>' + company.zip + '</td><td>' + company.city + '</td></tr>';
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
        }
    });
    PersonsRefresh();
}


$("#deletePersonBtn").click(function ()
{

    $('[type="checkbox"].checkmarkPerson').each(function () {
        var row = (this.checked ? $(this).val() : "");


        //now test by coloring the row:
        if (this.checked === true) {
            console.log(row.valueOf());
            PersonDelete(row.valueOf());
        }
    });


});



$("#addPersonBtn").click(function ()
{

    var rows = '<tr class="tmpPersonRow"><td>' + '</td><td>' + '</td><td>' + '<input type="text" name="firstname" required />' + '</td><td>' + '<input type="text" name="lastname" required />' + '</td><td>' + '<input type="text" name="street" required />' + '</td><td>' + '<input type="text" name="additionalinfo" required />' + '</td><td>' + '<input type="number" name="zipcode" min="500" required />' + '</td><td>' + '<input type="text" name="city" required />' + '</td></tr>';
    $('#TablePersons').append(rows);

    $("#pSubmit").remove();
    $("#pCancel").remove();
    $('#personBtns').append('<button type="submit" id="pSubmit" value="" onclick="TablePersonAdd();"><img src="assets/images/applyicon.png" class="button-table1" alt="applyicon.png"/></button>');
    $('#personBtns').append(" ");
    $('#personBtns').append('<button type="submit" id="pCancel" value="" onclick="TablePersonRemove();"><img src="assets/images/cancelicon.png" class="button-table1" alt="cancelicon.png"/></button>');

});

$("#addCompanyBtn").click(function ()
{
    var rows = '<tr class="tmpCompanyRow"><td>' + '</td><td>' + '</td><td>' + '<input type="text" name="company" required />' + '</td><td>' + '<input type="text" name="description" required />' + '</td><td>' + '<input type="number" name="marketvalue" required />' + '</td><td>' + '<input type="number" name="employees" min="1" required />' + '</td><td>' + '<input type="text" name="city" required />' + '</td></tr>';
    $('#TableCompanies').append(rows);
    
    $("#cSubmit").remove();
    $("#cCancel").remove();
    $('#companyBtns').append('<button type="submit" id="cSubmit" value="" onclick="TableCompanyAdd();"><img src="assets/images/applyicon.png" class="button-table1" alt="applyicon.png"/></button>');
    $('#companyBtns').append(" ");
    $('#companyBtns').append('<button type="submit" id="cCancel" value="" onclick="TableCompanyRemove();"><img src="assets/images/cancelicon.png" class="button-table1" alt="cancelicon.png"/></button>');

});

function TablePersonAdd()
{
    $('.tmpPersonRow').each(function (i, obj) {

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

    PersonsRefresh();
}

function TablePersonRemove()
{
    $(".tmpPersonRow").remove();
    $("#pSubmit").remove();
    $("#pCancel").remove();
}

function TableCompanyRemove()
{
    $(".tmpCompanyRow").remove();
    $("#cSubmit").remove();
    $("#cCancel").remove();  
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

function TableCompanyRemove()
{
    $(".tmpCompanyRow").remove();
}


