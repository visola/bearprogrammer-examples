$(() => {
  // Bind all links to push state
  $('a').on('click', (e) => {
    e.preventDefault();

    const $el = $(e.target);

    $('p').html($el.html());
    history.pushState({}, $el.text(), $el.attr('href'));
  })
});
