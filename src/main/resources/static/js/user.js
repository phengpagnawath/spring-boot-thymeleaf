
$('.btn-delete').on('click', function (e) {
    e.preventDefault()
    $('#modal-Confirm').modal('show')
    let id = $(this).data('id')
    $('.btn-confirm-delete').attr('href', '/admin/user/' + id + '/delete')
})

$('.btn-view').on('click', function (e) {
    e.preventDefault()
    $('#modal-view-user').modal('show')
    let firstName = $(this).data('first-name')
    let lastName = $(this).data('last-name')
    let email = $(this).data('email')
    $('.name').text(firstName + ' ' + lastName)
    $('.email').text(email)
})