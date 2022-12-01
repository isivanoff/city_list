$(document).ready(function () {
    $("#edit_name_form").on("submit", (ev) => updateName(ev));
    getCities($("#search-input").val());

    $("#search-button").on("click", function () {
        getCities($("#search-input").val());
    });

});



function getCities(name, page = 0) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    fetch(`http://localhost:8080/cities/all?name=${name}&page=${page}`, requestOptions)
        .then(response => response.json())
        .then(result => {
            $("#city_body").html("");
            showCities(result.content);
            updateNav(result, name);
        })
        .catch(error => console.log('error', error));

}

function showCities(data) {
    for (const city of data) {
        $(`<tr>
            <td>${city.name}</td>
            <td><img class="rounded mx-auto d-block city-image" src="${city.photo}"/></td>
            <td>
                <button type="button" class="btn btn-primary" onClick="renderEditName('${city.name}',${city.id})" data-bs-toggle="modal" data-bs-target="#editName">
                    Edit Name
                </button>
            </td>
            </tr>`).appendTo("#city_body");
    }

}

function renderEditName(name, id) {
    $("#edit_name").val(name);
    $("#cityNameId").val(id);

}

function updateName(ev) {
    ev.preventDefault();
    getCities($("#search-input").val());
    console.log($("#edit_name").val());
    console.log($("#cityNameId").val());
    //TODO Add update name function
}

function updateNav(data, name) {
    $("#firstPage").off("click");
    $("#prevPage").off("click");
    $("#curPage").off("click");
    $("#nextPage").off("click");
    $("#lastPage").off("click");

    $("#firstPage").on("click", function () {
        getCities(name);
    });

    if (data.pageable.pageNumber == 0) {
        $("#prevPage").closest("nav").hide();
    } else {
        $("#prevPage").closest("nav").show();
        $("#prevPage").text(data.pageable.pageNumber);
        $("#prevPage").on("click", function () {
            getCities(name, data.pageable.pageNumber - 1);
        });
    }

    $("#curPage").text(data.pageable.pageNumber + 1)


    if (data.pageable.pageNumber >= data.totalPages - 1) {
        $("#nextPage").closest("nav").hide();
    } else {
        $("#nextPage").closest("nav").show();
        $("#nextPage").text(data.pageable.pageNumber + 2);
        $("#nextPage").on("click", function () {
            getCities(name, data.pageable.pageNumber + 1);
        });
    }

    $("#lastPage").on("click", function () {
        getCities(name, data.totalPages - 1);
    });

}