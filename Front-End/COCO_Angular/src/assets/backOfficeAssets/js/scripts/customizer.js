/*=========================================================================================
  File Name: customizer.js
  Description: Template customizer js.
  ----------------------------------------------------------------------------------------
  Item Name: Modern Admin - Clean Bootstrap 4 Dashboard HTML Template
  Author: Pixinvent
  Author URL: hhttp://www.themeforest.net/user/pixinvent
==========================================================================================*/

(function (window, document, $) {
  'use strict';

  /********************************
  *           Customizer          *
  ********************************/
  // Customizer toggle & close button click events  [Remove customizer code from production]
  $('.customizer-toggle').on('click', function () {
    $('.customizer').toggleClass('open');
  });
  $('.customizer-close').on('click', function () {
    $('.customizer').removeClass('open');
  });
  if ($('.customizer-content').length > 0) {
    var customizer_content = new PerfectScrollbar('.customizer-content');

  }

  /************************************
  *           Layout Options          *
  ************************************/
  var body = $('body'),
    header_navbar = $('nav.header-menuSideBar'),
    horz_header_navbar = $('div.header-menuSideBar'),
    footer = $('footer'),
    menu = $('.main-menu'),
    menu_type = body.data('menu'),
    horz_layout = false,
    semiLight = false,
    semiDark = false;

  if (header_navbar.hasClass('menuSideBar-semi-light')) {
    semiLight = true;
  }
  if (header_navbar.hasClass('menuSideBar-semi-dark')) {
    semiDark = true;
  }

  if (menu_type == 'horizontal-menu') {
    horz_layout = true;
    $('.layout-options .menuSideBar').parent('.nav-item').attr('style', 'display: none !important');
    $('.color-options .nav-semi-light').parent('.nav-item').attr('style', 'display: none !important');
    $('.color-options .nav-semi-dark').parent('.nav-item').attr('style', 'display: none !important');
    $('#native-scroll').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#bordered-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#collapsible-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#static-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#flipped-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');

    $('.color-options li:eq(3) a').tab('show');
  }

  if (menu_type === 'vertical-compact-menu' || menu_type === 'vertical-content-menu' || menu_type === 'vertical-overlay-menu') {
    $('.color-options a#color-opt-3').tab('show');
    if (menu_type === 'vertical-content-menu') {
      $('.color-options a#color-opt-4').tab('show');
    }
    if (menu_type === 'vertical-compact-menu' || menu_type === 'vertical-overlay-menu') {
      $('#boxed-layout').parent('.custom-checkbox').attr('style', 'display: none !important');
    }
    $('.color-options .nav-semi-light').parent('.nav-item').attr('style', 'display: none !important');
    $('.color-options .nav-semi-dark').parent('.nav-item').attr('style', 'display: none !important');
  }

  if (menu_type === 'vertical-content-menu') {
    $('#fixed-layout').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#native-scroll').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#static-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('.layout-options .menuSideBar').parent('.nav-item').attr('style', 'display: none !important');
  }

  if (menu_type === 'vertical-compact-menu') {
    $('#right-side-icons').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#collapsible-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#brand-center').parent('.custom-checkbox').attr('style', 'display: none !important');
  }

  if (menu_type === 'vertical-overlay-menu') {
    $('#brand-center').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#native-scroll').parent('.custom-checkbox').attr('style', 'display: none !important');
  }

  if (menu_type === 'vertical-menu-modern') {
    $('#brand-center').parent('.custom-checkbox').attr('style', 'display: none !important');
  }

  // Layouts

  // If overlay menu template then collapsed sidebar should be checked by default
  if (menu_type === 'vertical-overlay-menu') {
    $('#collapsed-sidebar').prop('checked', true);
    $('#static-layout').parent('.custom-checkbox').attr('style', 'display: none !important');
    $('#static-navigation').parent('.custom-checkbox').attr('style', 'display: none !important');
  }

  $('#collapsed-sidebar').on('click', function () {

    // Toggle menu
    $.app.menu.toggle();

    setTimeout(function () {
      $(window).trigger("resize");
    }, 100);
  });
  $('#fixed-layout').on('click', function () {
    if ($('#boxed-layout').prop('checked') === true) {
      $('#boxed-layout').trigger('click');
    }
    if ($(this).prop('checked') === true) {
      // body.addClass("footer-fixed")
      if (!body.hasClass('footer-fixed')) {
        body.addClass('footer-fixed');
      }
      if (!body.hasClass('fixed-menuSideBar') && horz_layout === false) {
        body.addClass('fixed-menuSideBar');
      }
      if (!header_navbar.hasClass('fixed-top') && horz_layout === false) {
        header_navbar.addClass('fixed-top');
      }
      if (!footer.hasClass('fixed-bottom')) {
        footer.addClass('fixed-bottom');
      }
      if (!horz_header_navbar.hasClass('menuSideBar-fixed') && horz_layout === true) {
        horz_header_navbar.addClass('menuSideBar-fixed');
      }
      header_navbar.removeClass('menuSideBar-static-top');
      horz_header_navbar.removeClass('menuSideBar-static');
      menu.removeClass('menu-static');
      footer.removeClass('footer-static');
    }
    else {
      footer.removeClass('fixed-bottom');
      body.removeClass("footer-fixed")
    }
  });

  $('#boxed-layout').on('click', function () {
    if ($('#fixed-layout').prop('checked') === true) {
      $('#fixed-layout').trigger('click');
    }
    if ($(this).prop('checked') === true) {
      if (!body.hasClass('container boxed-layout')) {
        body.addClass('container boxed-layout');
      }
      if (!header_navbar.hasClass('container boxed-layout')) {
        header_navbar.addClass('container boxed-layout');
      }
      header_navbar.removeClass('menuSideBar-static-top');
      menu.removeClass('menu-static');
      footer.removeClass('footer-static');
    }
    else {
      body.removeClass('container boxed-layout');
      header_navbar.removeClass('container boxed-layout');
    }
  });

  $('#static-layout').on('click', function () {
    if ($(this).prop('checked') === true) {
      if (!header_navbar.hasClass('menuSideBar-static-top')) {
        header_navbar.addClass('menuSideBar-static-top');
      }
      if (!menu.hasClass('menu-static')) {
        menu.addClass('menu-static');
      }
      if (!footer.hasClass('footer-static')) {
        footer.addClass('footer-static');
      }
      if (horz_layout === true) {
        horz_header_navbar.unstick();
        horz_header_navbar.addClass('menuSideBar-static');
      }
      body.removeClass('fixed-menuSideBar');
      header_navbar.removeClass('fixed-top');
      horz_header_navbar.removeClass('menu-fixed');
      menu.removeClass('menu-fixed');
      footer.removeClass('fixed-bottom');
      $.app.menu.manualScroller.disable();
    }
    else {
      if (horz_layout === false) {
        body.addClass('fixed-menuSideBar');
        menu.removeClass('menuSideBar-static').addClass('menu-fixed');
        header_navbar.removeClass('menuSideBar-static-top').addClass('fixed-top');
      }
      if (horz_layout === true) {
        horz_header_navbar.sticky();
        horz_header_navbar.removeClass('menuSideBar-static').addClass('menuSideBar-fixed');
      }
      footer.removeClass('footer-static');
      $.app.menu.manualScroller.enable();
    }
  });

  // Navbar
  if (menu_type === 'vertical-overlay-menu') {
    $('#brand-center').prop('checked', true);
  }
  $('#brand-center').on('click', function () {
    if (!header_navbar.hasClass('menuSideBar-brand-center')) {
      if (semiLight == true) {
        header_navbar.removeClass('menuSideBar-semi-light');
      }
      if (semiDark == true) {
        header_navbar.removeClass('menuSideBar-semi-dark');
      }
      header_navbar.addClass('menuSideBar-dark menuSideBar-brand-center');
      changeLogo('light');
    }
    else {
      if (semiLight == true) {
        header_navbar.removeClass('menuSideBar-dark menuSideBar-brand-center');
        changeLogo('dark');
        header_navbar.addClass('menuSideBar-semi-light');
      }
      if (semiDark == true) {
        header_navbar.removeClass('menuSideBar-dark menuSideBar-brand-center');
        changeLogo('light');
        header_navbar.addClass('menuSideBar-semi-dark');
      }
    }
  });
  $('#menuSideBar-static-top').on('click', function () {
    if ($(this).prop('checked') === true) {
      if (!header_navbar.hasClass('menuSideBar-static-top')) {
        header_navbar.addClass('menuSideBar-static-top');
      }
      if (!menu.hasClass('menu-static')) {
        menu.addClass('menu-static');
      }
      if (!footer.hasClass('footer-static')) {
        footer.addClass('footer-static');
      }
      body.removeClass('fixed-menuSideBar');
      header_navbar.removeClass('fixed-top');
      menu.removeClass('menu-fixed');
      footer.removeClass('fixed-bottom');
      $.app.menu.manualScroller.disable();
    }
    else {
      body.addClass('fixed-menuSideBar');
      header_navbar.removeClass('menuSideBar-static-top').addClass('fixed-top');
      menu.removeClass('menu-static').addClass('menu-fixed');
      footer.removeClass('footer-static');
      $.app.menu.manualScroller.enable();
    }
  });


  // Navigation
  $('#native-scroll').on('click', function () {
    if ($('#static-navigation').prop('checked') === true) {
      menu.removeClass('menu-static').addClass('menu-fixed');
      $('#static-navigation').attr('checked', false);
    }
    if (!menu.hasClass('menu-native-scroll')) {
      menu.addClass('menu-native-scroll');
      $.app.menu.manualScroller.disable();
    }
    else {
      menu.removeClass('menu-native-scroll');
      $.app.menu.manualScroller.enable();
    }
  });
  $('#right-side-icons').on('click', function () {
    if (!menu.hasClass('menu-icon-right')) {
      menu.addClass('menu-icon-right');
    }
    else {
      menu.removeClass('menu-icon-right');
    }

    if (horz_layout === true) {
      if (!horz_header_navbar.hasClass('menuSideBar-icon-right')) {
        horz_header_navbar.addClass('menuSideBar-icon-right');
      }
      else {
        horz_header_navbar.removeClass('menuSideBar-icon-right');
      }
    }
  });
  $('#bordered-navigation').on('click', function () {
    if (!menu.hasClass('menu-bordered')) {
      menu.addClass('menu-bordered');
    }
    else {
      menu.removeClass('menu-bordered');
    }
  });
  $('#flipped-navigation').on('click', function () {
    if (!body.hasClass('menu-flipped')) {
      body.addClass('menu-flipped');
      $('.customizer-close').trigger('click');
    }
    else {
      body.removeClass('menu-flipped');
    }

    if (horz_layout === true) {
      if (!horz_header_navbar.hasClass('menuSideBar-flipped')) {
        horz_header_navbar.addClass('menuSideBar-flipped');
      }
      else {
        horz_header_navbar.removeClass('menuSideBar-flipped');
      }
    }
  });
  $('#collapsible-navigation').on('click', function () {
    if (!menu.hasClass('menu-collapsible')) {
      menu.addClass('menu-collapsible');
    }
    else {
      menu.removeClass('menu-collapsible');
    }
  });
  $('#static-navigation').on('click', function () {
    if ($('#native-scroll').prop('checked') === true) {
      menu.removeClass('menu-native-scroll');
      $('#native-scroll').attr('checked', false);
    }
    if (!menu.hasClass('menu-static')) {
      menu.addClass('menu-static').removeClass('menu-fixed');
      $.app.menu.manualScroller.disable();
    }
    else {
      menu.removeClass('menu-static').addClass('menu-fixed');
      $.app.menu.manualScroller.enable();
    }
  });



  /****************************************
  *           Change menu bg color        *
  ****************************************/
  if ($('.main-menu').hasClass('menu-dark')) {
    $('.customizer-sidebar-options').find('[data-sidebar="menu-dark"]').addClass('active').siblings('btn').removeClass('active');
  }
  else {
    $('.customizer-sidebar-options').find('[data-sidebar="menu-light"]').addClass('active').siblings('btn').removeClass('active');
  }

  $('.customizer-sidebar-options .btn').on('click', function () {
    var $this = $(this),
      sidebarColor = $this.attr('data-sidebar');
    $this.addClass('active').siblings('.btn').removeClass('active');
    $('.main-menu').removeClass('menu-dark menu-light').addClass(sidebarColor);
    if (horz_layout === true) {
      horz_header_navbar.removeClass('menuSideBar-dark menuSideBar-light');
      if (sidebarColor == 'menu-light') {
        horz_header_navbar.addClass('menuSideBar-light');
      }
      else {
        horz_header_navbar.addClass('menuSideBar-dark');
      }
    }
  });


  /*********************************
  *           Color Options        *
  *********************************/

  var el = $('nav.header-menuSideBar'),
    nav_type = 'menuSideBar-semi-light',
    bgClass = '';
  if (el.attr('class').match(/\bbg-\S+/g)) {
    bgClass = el.attr('class').match(/\bbg-\S+/g)[0];
  }

  // Nav Semi Light
  $('.nav-semi-light').on('click', function () {
    chkBgClass(el);
    changeLogo('dark');
    resetBgClass(el);
    addBgClass(el, 'menuSideBar-semi-light bg-gradient-x-grey-blue');
    $('input[name=nav-slight-clr].default').prop('checked', true);
    semiLight = true;
    semiDark = false;
  });
  $("input[name='nav-slight-clr']").change(function () {
    // bgClass = el.attr('class').match(/\bbg-\S+/g)[0];
    if (semiDark == true) {
      el.removeClass('menuSideBar-semi-dark').addClass('menuSideBar-semi-light');
    }
    bgClass = chkBgClass(el);
    el.removeClass(bgClass).addClass($(this).data('bg'));
  });

  // Nav Semi Dark
  $('.nav-semi-dark').on('click', function () {
    chkBgClass(el);
    changeLogo('light');
    resetBgClass(el);
    addBgClass(el, 'menuSideBar-semi-dark menuSideBar-shadow');
    $('input[name=nav-sdark-clr].default').prop('checked', true);
    semiLight = false;
    semiDark = true;
  });
  $("input[name='nav-sdark-clr']").change(function () {
    if (semiLight == true) {
      el.removeClass('menuSideBar-semi-light').addClass('menuSideBar-semi-dark');
    }
    var el = $('.menuSideBar-header');
    var bgClass = chkBgClass(el);
    el.removeClass(bgClass).addClass($(this).data('bg'));
  });

  // Nav Dark
  $('.nav-dark').on('click', function () {
    chkBgClass(el);
    changeLogo('light');
    resetBgClass(el);
    addBgClass(el, 'menuSideBar-dark');
    $('input[name=nav-dark-clr].default').prop('checked', true);
  });
  $("input[name='nav-dark-clr']").change(function () {
    if (!el.hasClass('menuSideBar-dark')) {
      $('.nav-dark').trigger('click');
    }
    var bgClass = chkBgClass(el);
    el.removeClass(bgClass).addClass($(this).data('bg'));
  });

  // Nav Light
  $('.nav-light').on('click', function () {
    chkBgClass(el);
    changeLogo('dark');
    resetBgClass(el);
    addBgClass(el, 'menuSideBar-light menuSideBar-shadow');
    $('input[name=nav-light-clr].default').prop('checked', true);
  });
  $("input[name='nav-light-clr']").change(function () {
    if (!el.hasClass('menuSideBar-light')) {
      $('.nav-light').trigger('click');
    }
    var bgClass = chkBgClass(el);
    el.removeClass(bgClass).addClass($(this).data('bg'));
  });

  function chkBgClass(elm) {
    if (elm.attr('class').match(/\bbg-\S+/g)) {
      bgClass = elm.attr('class').match(/\bbg-\S+/g);
      var classes = '';
      $.map(bgClass, function (k, v) {
        if (v === 0)
          classes = k;
        else
          classes = classes + ' ' + k;
      });
      bgClass = classes;
    }
    else
      bgClass = '';

    return bgClass;
  }

  function resetBgClass(elm) {
    elm.removeClass('menuSideBar-semi-dark menuSideBar-semi-light menuSideBar-light menuSideBar-dark menuSideBar-shadow ' + bgClass);
    var bgClassHeader = '';
    if ($('.menuSideBar-header').attr('class').match(/\bbg-\S+/g)) {
      bgClassHeader = $('.menuSideBar-header').attr('class').match(/\bbg-\S+/g)[0];
    }
    $('.menuSideBar-header').removeClass(bgClassHeader);
  }

  function addBgClass(elm, classes) {
    elm.addClass(classes);
  }

  function changeLogo(logo) {
    if (logo == 'light') {
      $('.brand-logo').attr('src', '../../../app-assets/images/logo/logo.png');
    }
    else {
      $('.brand-logo').attr('src', '../../../app-assets/images/logo/logo.png');
    }
  }

  // Check local storage if menu is collapsed or not
  var menuToggle;
  if (typeof (Storage) !== "undefined") {
    menuToggle = localStorage.getItem("menuLocked");
  }

  if (menuToggle === "false") {
    $('#collapsed-sidebar').prop('checked', true);
  }
})(window, document, jQuery);
