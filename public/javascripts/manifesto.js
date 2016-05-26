
if (window.console) {
  console.log("Like engineering - do ya?");
}

var _Drop;

_Drop = Drop.createContext({
  classPrefix: 'drop'
});

var drops = $('.drop-container');

drops.each(function() {
  var drop;
  var theme = 'drop-theme-arrows-bounce';
  var $subpoint = $(this);
  var $target = $subpoint.find('.drop-target');
  $target.addClass(theme);
  return drop = new _Drop({
    target: $target[0],
    classes: theme,
    position: 'bottom center',
    constrainToWindow: true,
    constrainToScrollParent: true,
    openOn: 'click',
    content: $subpoint.find('.drop-content').html()
    });
});