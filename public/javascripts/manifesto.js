
if (window.console) {
  console.log("Like engineering?");
}

var _Drop;

_Drop = Drop.createContext({
  classPrefix: 'drop'
});

var drops = $('.drop-container');

drops.each(function() {
  var theme = 'drop-theme-arrows-bounce';
  var $subpoint = $(this);
  var $target = $subpoint.find('.drop-target');
  $target.addClass(theme);
  var pos = $target.data("position") || 'bottom center';
  return new _Drop({
    target: $target[0],
    classes: theme,
    position: pos,
    constrainToWindow: false,
    constrainToScrollParent: true,
    openOn: 'click',
    content: $subpoint.find('.drop-content').html()
    });
});
