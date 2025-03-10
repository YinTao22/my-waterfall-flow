var lazyload = new yall();
window.addEventListener('DOMContentLoaded', (e) => {
    lazyload.run();
});

function scrollup() {
    scroll(0, 0);
}

function hd_images() {
    var get_html = document.querySelector("html").innerHTML;
    get_html = get_html.replace(/\?w=360"/g, '?w=1080"');
    document.body.innerHTML = get_html;
    lazyload.run();
    images_zoom();
}

function zoom_out() {
    var get_column = document.querySelector('div.grid-container');
    var column_num = Number(get_column.style.columnCount);
    var text_block = document.getElementsByClassName('text');
    get_column.style.columns = column_num + 1;
    if (column_num + 1 > 6) {
        Array.from(text_block).forEach(
            function(text_block) {
                text_block.style.display = "none";
            }
        );
    } else {
        Array.from(text_block).forEach(
            function(text_block) {
                text_block.style.display = "block";
            }
        );
    }
}

function zoom_in() {
    var get_column = document.querySelector('div.grid-container');
    var column_num = Number(get_column.style.columnCount);
    var text_block = document.getElementsByClassName('text');
    get_column.style.columns = column_num - 1;
    if (column_num - 1 > 6) {
        Array.from(text_block).forEach(
            function(text_block) {
                text_block.style.display = "none";
            }
        );
    } else {
        Array.from(text_block).forEach(
            function(text_block) {
                text_block.style.display = "block";
            }
        );
    }

}

function images_zoom() {
    mediumZoom('img', {
        margin: 50,
        background: 'rgba(23, 29, 54, .8)',
        scrollOffset: 0,
        metaClick: false
    })
}