function fetchDetailsAndLoad() {
  $.get("http://localhost:8080/getDetails", (data, status) => {
    $('#table-body').empty();
    for (let i = 0; i < data.length; i++) {
      const element = data[i];
      $('#table-body').append(
        "<tr>"
        +"<td>"+element.vehicleid+"</td>"
        +"<td>"+element.slotnumber+"</td>"
        +"<td>"+element.intime+"</td>"
        +"</tr>"
      );
    }
  });
}

fetchDetailsAndLoad();

$('#my-form').on('submit', () => {
  vehicleid = $('#vehicleid').val()
  option = $('input[name=option]:checked').val();
  $.post("http://localhost:8080/home", {option:option, vehicleid:vehicleid}, (data) => {
    Materialize.toast(data, 2500, 'toast-color' );
    fetchDetailsAndLoad();
  });
  return false;
});