$('#btn-add').on('click',function (e) {
e.preventDefault()
$('.modal-title').text('Add Roles')
$('.form-control').val('0')
$('#name').val(null)
})