
$('.btn-delete').on('click', function (e) {
    e.preventDefault()
    $('#modal-Confirm').modal('show')
    let id = $(this).data('id')
    $('.btn-confirm-delete').attr('href', '/admin/user/' + id + '/delete')
})