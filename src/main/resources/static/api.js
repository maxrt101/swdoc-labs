
// Custom Alerts

function autoClose(element) {
    setTimeout(function () {
        element.remove();
    }, 5000)
}

const alert = ({message, type = 'success'}) => {
    let $wrapper = $('<div>').addClass(`alert alert-${type} alert-dismissible`).attr({role: 'alert'});
    $wrapper.html(`<div>${message}</div><button type="button" class="btn-close" data-bs-dismiss="alert"></button>`);
    $('#alerts').prepend($wrapper);
    autoClose($wrapper);
}

// API

function clearAddFilm() {
    $("#create-film-id").val('')
    $("#create-film-name").val('')
    $("#create-film-description").val('')
    $("#create-film-director").val('')
    $("#create-film-box").val('')
}

function addFilm() {
    let newFilm = {
        id: parseInt($("#create-film-id").val()),
        name: $("#create-film-name").val(),
        description: $("#create-film-description").val(),
        director: $("#create-film-director").val(),
        boxOffice: parseInt($("#create-film-box").val())
    }

    $.ajax({
        type: 'POST',
        url: '/api/entity/film',
        contentType: 'application/json',
        data: JSON.stringify(newFilm),
        success: (response) => {
            console.log(`[+] POST /api/entity/film ${JSON.stringify(newFilm)}`)
            alert({message: 'Film created successfully', type: 'success'})
            clearAddFilm()
            $('#create-film').modal("hide")
        },
        error: (error) => {
            console.log(`[!] POST /api/entity/film ${JSON.stringify(newFilm)} FAILED`)
            alert({message: 'Film creation failed', type: 'warning'})
        }
    })
}

function editFilm(id) {
    $.get(`/api/entity/film/${id}`, (data) => {
        console.log(`[+] GET /api/entity/films/${id} `)
        $("#edit-film-id").val(data.id)
        $("#edit-film-name").val(data.name)
        $("#edit-film-description").val(data.description)
        $("#edit-film-director").val(data.director)
        $("#edit-film-box").val(data.boxOffice)
        $("#edit-film-rating").val(data.rating)
        $("#edit-film-rated").val(data.usersRated)
        $('#edit-film').modal('show')
    })
}

function saveEditFilm() {
    let film = {
        id: parseInt($("#edit-film-id").val()),
        name: $("#edit-film-name").val(),
        description: $("#edit-film-description").val(),
        director: $("#edit-film-director").val(),
        boxOffice: parseInt($("#edit-film-box").val()),
        rating: parseFloat($("#edit-film-rating").val()),
        usersRated: parseInt($("#edit-film-rated").val())
    }

    $.ajax({
        type: 'PUT',
        url: `/api/entity/film/${film.id}`,
        contentType: 'application/json',
        data: JSON.stringify(film),
        success: (response) => {
            console.log(`[+] PUT /api/entity/film/${film.id} ${JSON.stringify(film)}`)
            alert({message: 'Film updated successfully', type: 'success'})
            clearAddFilm()
            $('#edit-film').modal('hide')
        },
        error: (error) => {
            console.log(`[!] PUT /api/entity/film/${film.id} ${JSON.stringify(film)} FAILED`)
            alert({message: 'Film update failed', type: 'warning'})
        }
    })
}

function deleteFilm(id) {
    $(this).closest('.card').remove()
    $.get(`/api/entity/review/remove_by_film/${id}`, (data) => {
        $.get(`/api/entity/actor_film/remove_by_film/${id}`, (data) => {
            $.ajax({
                type: 'DELETE',
                url: '/api/entity/film/' + id
            }).done(() => {
                console.log(`[+] DELETE /api/entity/film/${id}`)
                alert({message: 'Film deletion succeeded', type: 'warning'})
            })
        })
    })
}

function populateFilms() {
    $.get('/api/entity/film', (data) => {
        console.log(`[+] GET /api/entity/film -> ${data.length}`)
        let filmsHtml = '<ul class="row">'
        data.forEach(film => {
            filmsHtml += `<div class="card col-3 m-1" style="width: 18rem;"><div class="card-body"><h5 class="card-title">${film.name}</h5><h6 class="card-subtitle mb-2 text-muted">Rating: ${film.rating}</h6><h6 class="card-subtitle mb-2 text-muted">BoxOffice: ${film.boxOffice}</h6><h6 class="card-subtitle mb-2 text-muted">Director: ${film.director}</h6><p class="card-text">${film.description}</p><a onclick="editFilm(${film.id})" class="btn btn-primary m-1">Edit</a><a onclick="deleteFilm(${film.id})" class="btn btn-danger m-1">Delete</a></div></div>`
        })
        filmsHtml += "</ul>"
        $('#film-list').html(filmsHtml)
    })
}
