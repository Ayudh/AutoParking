prevData = []
console.log("ready function");
$.get("http://localhost:8080/getDetails", (data, status) => {
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
  prevData = []
  for (let i = 0; i < data.length; i++) {
    const element = data[i];
    prevData.push(data[i].vehicleid);
  }

});
$('#my-form').on('submit', () => {
  console.log("form submitted");

  vehicleid = $('#vehicleid').val()
  option = "park";

  $.post("http://localhost:8080/home", {option:option, vehicleid:vehicleid}, (data) => {
  	console.log(data);
  	Materialize.toast(data, 2500, 'toast-color' );
  	$.get("http://localhost:8080/getDetails", (data, status) => {
    console.log(prevData);
    console.log(data);
    filteredData = data.filter((x) => {
      return prevData.indexOf(x.vehicleid) == -1;
    })
    console.log(filteredData);
    for (let i = 0; i < filteredData.length; i++) {
      const element = filteredData[i];
      $('#table-body').append(
        "<tr class='new-vehicle'>"
        +"<td>"+element.vehicleid+"</td>"
        +"<td>"+element.slotnumber+"</td>"
        +"<td>"+element.intime+"</td>"
        +"</tr>"
	      )
	  }
	    prevData = []
	    for (let i = 0; i < data.length; i++) {
	      const element = data[i];
	      prevData.push(data[i].vehicleid);
	    }
	    $('.new-vehicle').toggleClass('glow');
	    setTimeout(() => {
	      $('.new-vehicle').toggleClass('glow');
	      $("tr").removeClass("new-vehicle");
	    }, 1000);
	  });
  });
  return false;
});