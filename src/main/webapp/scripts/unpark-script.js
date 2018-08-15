prevData = []
console.log("ready function");
$.get("http://localhost:8080/getDetails", (data, status) => {
  for (let i = 0; i < data.length; i++) {
    const element = data[i];
    $('#table-body').append(
      "<tr class='new-vehicle'>"
      +"<td>"+element.vehicleid+"</td>"
      +"<td>"+element.slotnumber+"</td>"
      +"<td>"+element.intime+"</td>"
      +"</tr>"
    );
  }
  prevData = data
});

$('#my-form').on('submit', () => {
  console.log("form submitted");

  vehicleid = $('#vehicleid').val()
  option = "unpark";

  $.post("http://localhost:8080/home", {option:option, vehicleid:vehicleid}, (data) => {
    console.log(data);
    Materialize.toast(data, 2500, 'toast-color' );

  $.get("http://localhost:8080/getDetails", (data, status) => {
    console.log(prevData);
    console.log(data);
    newVehicleIds = []
    for (let i = 0; i < data.length; i++) {
      const element = data[i];
      newVehicleIds.push(data[i].vehicleid);
    }
    console.log(newVehicleIds);
    $('#table-body').empty();
    for (let i = 0; i < prevData.length; i++) {
      const element = prevData[i];
      if (newVehicleIds.indexOf(element.vehicleid) == -1) {
        row = "<tr class='new-vehicle'>";
      } else {
        row = "<tr>"
      }
      $('#table-body').append(
        row
        +"<td>"+element.vehicleid+"</td>"
        +"<td>"+element.slotnumber+"</td>"
        +"<td>"+element.intime+"</td>"
        +"</tr>"
      )
    }
    prevData = data
    $('.new-vehicle').toggleClass('glow');
    setTimeout(() => {
      $('.new-vehicle').toggleClass('glow');
      // $("tr").removeClass("new-vehicle");
      $('.new-vehicle').remove();
    }, 1000);
  });
});
  return false;
});