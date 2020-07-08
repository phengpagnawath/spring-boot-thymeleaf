$('.btnEdit').on('click', function (e) {
    e.preventDefault()
    let id = $(this).data('id')
    let name = $(this).data('name')
    $('#form').attr('action', '/admin/categories/' + id)
    $('#name').val(name)
})

$('.btnDelete').on('click', function (e) {
    e.preventDefault()
    $('#modal-Confirm').modal('show')
    let id = $(this).data('id')
    $('.btn-confirm-delete').attr('href', '/admin/categories/' + id + '/delete')
})