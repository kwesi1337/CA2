/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//JQuery
$(document).ready(function ()
{
    PersonsRefresh();

    $("#PersonsRefresh").click(function ()
    {
        PersonsRefresh();
    });
    
    CompanyRefresh();

    $("#CompaniesRefresh").click(function ()
    {
        CompaniesRefresh();
    });

});



function PersonsRefresh()
{
    $.getJSON("api/person/complete/", function (persons)
    {
        $('#TablePersons tr').remove();
        var rows = "";
        rows += '<tr><th>' + "ID" + '</th><th>' + "First name" + '</th><th>' + "Last name" + '</th><th>' + "Street" + '</th><th>' + "Zipcode" + '</th><th>' + "City" + '</th><th>' + "DELETE ROW" + '</th></tr>';
        $.each(persons, function (i, person)
        {
            rows += '<tr><td>' + person.id + '</td><td>' + person.firstname + '</td><td>' + person.lastname + '</td><td>' + person.address + '</td><td>' + person.zip + '</td><td>' + person.city + '</td><td><input type="button" onclick="PersonDelete(' + person.id + ')" value="Delete" /></td></tr>';
        });

        $('#TablePersons').append(rows);
    });
}

function PersonDelete(id)
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

function CompaniesRefresh()
{
    $.getJSON("api/company/complete/", function (companies)
    {
        $('#TableCompanies tr').remove();
        var rows = "";
        rows += '<tr><td>' + "ID" + '</td><td>' + "First name" + '</td><td>' + "Last name" + '</td><td>' + "Street" + '</td><td>' + "Zipcode" + '</td><td>' + "City" + '</td></tr>';
        $.each(companies, function (i, company)
        {
           rows += '<tr><td>' + company.id + '</td><td>' + company.firstname + '</td><td>' + company.lastname + '</td><td>' + company.address + '</td><td>' + company.zip + '</td><td>' + company.city + '</td><td><input type="button" onclick="PersonDelete(' + company.id + ')" value="Delete" /></td></tr>';
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
        success: function () {}
    });
}