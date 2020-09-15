$('#btn-add').on('click',function (e) {
    e.preventDefault()
    $('.modal-title').text('Add new article')
    $('.form-control').val('0')
    $('#title').val(null)
    $('#description').val(null)
    $('#thumbnail').val(null)
})
$('.btnEdit').on('click', function (e) {
    e.preventDefault()
    $('.modal-title').text('Update article')
    let article = {
        articleID:$(this).data('id'),
        title : $(this).data('title'),
        thumbnail : $(this).data('thumbnail'),
        description : $(this).data('description'),
        category : $(this).data('category-id')
    }
    $('#quickForm').attr('action', '/admin/articles/'+article.articleID)
    $('#categoryId').val(article.category)
    $('#title').val(article.title)
    $('#description').val(article.description)
    $('#thumbnail').val(article.thumbnail)
    $('#article-modal').modal('show')
})

$('.btn-delete').on('click', function (e) {
    e.preventDefault()
    $('#modal-Confirm').modal('show')
    let id = $(this).data('id')
    $('.btn-confirm-delete').attr('href', '/admin/articles/' + id + '/delete')
})

$('.btn-view').on('click', function (e) {
    e.preventDefault()
    $('#modal-view-article').modal('show')
    $('.title').text($(this).data('title'))
    $('.description').text($(this).data('description'))
    $('.author').text($(this).data('author'))
    $('.thumbnail').attr("src","/images/"+$(this).data('thumbnail'))
    $('.published-date').text($(this).data('published-date'))
})