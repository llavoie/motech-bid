(function () {
    'use strict';

    var directives = angular.module('bid.directives', []);

    directives.directive('patientJqgridSearch', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var elem = angular.element(element),
                    table = angular.element('#' + attrs.patientJqgridSearch),
                    eventType = elem.data('event-type'),
                    timeoutHnd,
                    filter = function (time) {
                        var field = elem.data('search-field'),
                            value = elem.data('search-value'),
                            type = elem.data('field-type') || 'string',
                            url = parseUri(jQuery('#' + attrs.patientJqgridSearch).jqGrid('getGridParam', 'url')),
                            query = {},
                            params = '?',
                            array = [],
                            prop;

                        for (prop in url.queryKey) {
                            if (prop !== field) {
                                query[prop] = url.queryKey[prop];
                            }
                        }

                        switch (type) {
                        case 'boolean':
                            query[field] = url.queryKey[field].toLowerCase() !== 'true';

                            if (query[field]) {
                                elem.find('i').removeClass('icon-ban-circle').addClass('icon-ok');
                            } else {
                                elem.find('i').removeClass('icon-ok').addClass('icon-ban-circle');
                            }
                            break;
                        case 'array':
                            if (elem.children().hasClass("icon-ok")) {
                                elem.children().removeClass("icon-ok").addClass("icon-ban-circle");
                            } else if (elem.children().hasClass("icon-ban-circle")) {
                                elem.children().removeClass("icon-ban-circle").addClass("icon-ok");
                                array.push(value);
                            }
                            angular.forEach(url.queryKey[field].split(','), function (val) {
                                if (angular.element('#' + val).children().hasClass("icon-ok")) {
                                    array.push(val);
                                }
                            });

                            query[field] = array.join(',');
                            break;
                        default:
                            query[field] = elem.val();
                        }

                        for (prop in query) {
                            params += prop + '=' + query[prop] + '&';
                        }

                        params = params.slice(0, params.length - 1);

                        if (timeoutHnd) {
                            clearTimeout(timeoutHnd);
                        }

                        timeoutHnd = setTimeout(function () {
                            jQuery('#' + attrs.patientJqgridSearch).jqGrid('setGridParam', {
                                page: 1,
                                url: '../bid/vax' /* + params */
                            }).trigger('reloadGrid');
                        }, time || 0);
                    };

                switch (eventType) {
                case 'keyup':
                    elem.keyup(function () {
                        filter(500);
                    });
                    break;
                case 'change':
                    elem.change(filter);
                    break;
                default:
                    elem.click(filter);
                }
            }
        };
    });

    directives.directive('patientGrid', function($compile, $http, $templateCache) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                try {
                    if (typeof($('#outsidePatientTable')[0].grid) !== 'undefined') {
                        return;
                    }
                }
                catch (e) {
                    return;
                }

                var elem = angular.element(element), filters;

                elem.jqGrid({
                    url: '../bid/vax',
                    datatype: 'json',
                    jsonReader:{
                        repeatitems:false
                    },
                    colModel: [{
                        name: 'externalId',
                        index: 'externalId',
                        width: 90
                    }, {
                        name: 'lastName',
                        index: 'lastName',
                        width: 90
                    }, {
                        name: 'firstName',
                        index: 'firstName',
                        width: 90
                    }, {
                        name: 'dateOfBirth',
                        index: 'dateOfBirth',
                        width: 130
                    }, {
                        name: 'gender',
                        index: 'gender',
                        width: 80
                    }, {
                        name: 'village',
                        index: 'village',
                        width: 90
                    }],
                    pager: '#' + attrs.patientGrid,
                    sortname: 'externalId',
                    sortorder: 'asc',
                    viewrecords: true,
                    gridComplete: function () {
                        angular.forEach(['externalId', 'lastName', 'firstName', 'dateOfBirth', 'gender',
                            'village'], function (value) {
                            elem.jqGrid('setLabel', value, scope.msg('bid.patient.' + value));
                        });
                        $('#outsidePatientTable').children('div').width('100%');
                        $('.ui-jqgrid-htable').addClass("table-lightblue");
                        $('.ui-jqgrid-btable').addClass("table-lightblue");
                        $('.ui-jqgrid-htable').width('100%');
                        $('.ui-jqgrid-bdiv').width('100%');
                        $('.ui-jqgrid-hdiv').width('100%');
                        $('.ui-jqgrid-view').width('100%');
                        $('#t_patientTable').width('auto');
                        $('.ui-jqgrid-pager').width('100%');
                        $('.ui-jqgrid-hbox').css({'padding-right':'0'});
                        $('.ui-jqgrid-hbox').width('100%');
                        $('#outsidePatientTable').children('div').each(function() {
                            $(this).find('table').width('100%');
                        });
                        /*
                        var startDateTextBox = angular.element('#dateTimeFrom'),
                            endDateTextBox = angular.element('#dateTimeTo');
                            */
                    }
                });
            }
        };
    });

}());