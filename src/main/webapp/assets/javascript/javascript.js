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
});



function PersonsRefresh()
{
    $.getJSON("api/person/complete/", function (persons)
    {
     $('#TablePersons tr').remove();
       var rows = "";
      rows += '<tr><td>' + "ID" + '</td><td>' + "First name" + '</td><td>'+ "Last name" + '</td><td>'+ "Street" +'</td><td>' + "Zipcode" +'</td><td>' + "City" +'</td></tr>';
        $.each(persons, function (i, person)
        {
            rows += '<tr><td>' + person.id + '</td><td>' + person.firstname + '</td><td>' + person.lastname + '</td><td>' + person.address + '</td><td>' + person.zip + '</td><td>' + person.city + '</td></tr>';
        });

        $('#TablePersons').append(rows);
    });
}