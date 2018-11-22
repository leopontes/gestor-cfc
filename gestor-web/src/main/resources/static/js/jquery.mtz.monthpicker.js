/*
 * jQuery UI Monthpicker
 *
 * @licensed MIT <see below>
 * @licensed GPL <see below>
 *
 * @author Luciano Costa
 * http://lucianocosta.info/jquery.mtz.monthpicker/
 *
 * Depends:
 *	jquery.ui.core.js
 */

/**
 * MIT License
 * Copyright (c) 2011, Luciano Costa
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * GPL LIcense
 * Copyright (c) 2011, Luciano Costa
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

(function ($) {

    var methods = {
        init : function (options) { 
            //return this.each(function () {
                var 
                    $this = $(this),
                    data = $this.data('monthpicker'),
                    year = (options && options.year) ? options.year : (new Date()).getFullYear(),
                    settings = $.extend({
                        pattern: 'mm/yyyy',
                        selectedMonth: null,
                        selectedMonthName: '',
                        selectedYear: year,
                        startYear: year - 10,
                        finalYear: year + 10,
                        monthNames: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                        id: "ui-monthpicker-div",//"monthpicker_" + (Math.random() * Math.random()).toString().replace('.', ''),
                        openOnFocus: true,
                        disabledMonths: [],
                        validMonths: [], //["01/2014", "02,2014"]
                        allowManualInput:true
                    }, options);

                settings.dateSeparator = settings.pattern.replace(/(mmm|mm|m|yyyy|yy|y)/ig,'');

                // If the plugin hasn't been initialized yet for this element
                if (!data) {
                    $(this).data('monthpicker', {
                        'target': $this,
                        'settings': settings,
						'_lastInput':undefined
                    });
                    
                    if( settings.allowManualInput ){
	                    $(this).keyup( formataData );
	                    $(this).keydown( doNumber );
                    }else{
                    	$(this).keydown( impedeEvento );
                    }
                    $(this).unbind("blur");
                    $(this).blur( function( event ) {
                    	var target = getSourceEvento( event );
                    	if($(target).val() == ""){
                    		return;
                    	}
                    	if(!global.validaData.blur( event )){
                    		return impedeEvento(event);
                    	}
                    	if(settings.validMonths && settings.validMonths.length && $.inArray($(target).val(), settings.validMonths) < 0){
            				alert( errors_date.replace("{0}",$(target).val())  );
            				impedeEvento( event );
            				
            				global.validaData.id = ID(target.id);
            				
            				window.setTimeout(function(){ $( global.validaData.id  ).focus(); $( global.validaData.id  ).select();}, 10);
            		
            				return impedeEvento( event );
                    	}
                    });
                    
                    $(this).attr("maxlength", "7");

                    if (settings.openOnFocus === true) {
                        $this.bind('focus', function ( e ) {
                            $this.monthpicker('show',e);
                        });
                    }

                    $this.monthpicker('mountWidget', settings);

                    $this.bind('monthpicker-click-month', function (e, month, year) {
                        $this.monthpicker('setValue', settings);
                        $this.monthpicker('hide');
                    });

                    // hide widget when user clicks elsewhere on page
                    $this.addClass("mtz-monthpicker-widgetcontainer");
                    $(document).unbind("mousedown.mtzmonthpicker").bind("mousedown.mtzmonthpicker", function (e) {
                        if (!e.target.className || e.target.className.toString().indexOf('mtz-monthpicker') < 0) {
                            $(".mtz-monthpicker-widgetcontainer").each(function () {
                                if (typeof($(this).data("monthpicker"))!="undefined") { 
                                    $(this).monthpicker('hide'); 
                                }
                            });
                        }
                    });
                }

            //});
        },

        show: function (n) {

        	var widget = $('#' + this.data('monthpicker').settings.id);
			this.data('monthpicker')._lastInput = (n && n.target) ? $(n.target) : $("#" + n);
            var monthpicker = this.data('monthpicker')._lastInput;//$('#' + this.data('monthpicker').target.attr("id").replace(":", "\\:") + ':eq(0)');
            widget.css("top", monthpicker.offset().top  + monthpicker.outerHeight());
            widget.css("left", monthpicker.offset().left);
            
            //comentar para poder digitar o mês tb
            //widget.find('select').focus();3
            if( monthpicker.val() != "" ){
            	widget.find('option').each( function(){
            		if( this.value == monthpicker.val().split("/")[1] ){
            			$(this).attr("selected", "selected");
            			widget.find('.mtz-monthpicker-year').change();
            			
            		}
            	});
            	
            	widget.find('TD.mtz-monthpicker-month A').each( function(){
            		if( parseFloat($(this).attr("data-month")) == parseFloat(monthpicker.val().split("/")[0]) ){
            			$(this).removeClass("ui-state-default");
            			$(this).addClass("ui-datepicker-current-day");
            		}else{
            			$(this).addClass("ui-state-default");
            			$(this).removeClass("ui-datepicker-current-day");            			
            		}
            	});
            }
            widget.fadeIn();
            this.trigger('monthpicker-show');
        },

        hide: function () {
            var widget = $('#' + this.data('monthpicker').settings.id);
            if (widget.is(':visible')) {
                widget.fadeOut();
                this.trigger('monthpicker-hide');
            }
        },

        setValue: function (settings) {
            var 
                month = settings.selectedMonth,
                year = settings.selectedYear;

            if(settings.pattern.indexOf('mmm') >= 0) {
                month = settings.selectedMonthName;
            } else if(settings.pattern.indexOf('mm') >= 0 && settings.selectedMonth < 10) {
                month = '0' + settings.selectedMonth;
            }

            if(settings.pattern.indexOf('yyyy') < 0) {
                year = year.toString().substr(2,2);
            } 

            if (settings.pattern.indexOf('y') > settings.pattern.indexOf(settings.dateSeparator)) {
                this.data('monthpicker')._lastInput.val(month + settings.dateSeparator + year);
            } else {
                this.data('monthpicker')._lastInput.val(year + settings.dateSeparator + month);
            }
        },

        disableMonths: function (months) {
            var 
                settings = this.data('monthpicker').settings,
                container = $('#' + settings.id);

            settings.disabledMonths = months;

            container.find('.mtz-monthpicker-month').each(function () {
                var m = parseInt($(this).data('month'));
                if ($.inArray(m, months) >= 0 ) {
                    $(this).find("A").addClass('ui-state-disabled');
                } else {
                    $(this).find("A").removeClass('ui-state-disabled');
                }
            });
        },

        mountWidget: function (settings) {
            var
                monthpicker = this,
                container = $('<div id="'+ settings.id +'" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" />'),
                header = $('<div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all mtz-monthpicker" />'),
                combo = $('<select class="mtz-monthpicker mtz-monthpicker-year" />'),
                table = $('<table class="mtz-monthpicker" />'),
                tbody = $('<tbody class="mtz-monthpicker" />'),
                tr = $('<tr class="mtz-monthpicker" />'),
                td = '';
                selectedYear = settings.selectedYear,
                option = null;

            container.css({
                position:'absolute',
                zIndex:999999,
                whiteSpace:'nowrap',
                width:'150px',
                overflow:'hidden',
                textAlign:'center',
                display:'none',
                top: monthpicker.offset().top + monthpicker.outerHeight(),
                left: monthpicker.offset().left
            });
            combo.css({width:'80px'})

            // mount years combo
            for (var i = settings.startYear; i <= settings.finalYear; i++) {
                var option = $('<option class="mtz-monthpicker" />').attr('value', i).append(i);
                if (settings.selectedYear === i) {
                    option.attr('selected', 'selected');
                }
                combo.append(option);
            }
            header.append(combo).appendTo(container);

            // mount months table
            for (var monthNumber=1; monthNumber<=12; monthNumber++) {
                td = $('<td class="mtz-monthpicker mtz-monthpicker-month" style="padding:2px;cursor:default;" />').attr('data-month',monthNumber);

                methods.createMonthElement(td, monthNumber, settings);
                
                tr.append(td).appendTo(tbody);
                if (monthNumber % 3 === 0) {
                    tr = $('<tr class="mtz-monthpicker" />'); 
                }
                

            }

            table.append(tbody).appendTo(container);

            container.find('.mtz-monthpicker-month').bind('click', function () {
                var m = parseInt($(this).data('month'));
                if ($.inArray(m, settings.disabledMonths) < 0 ) {
                    settings.selectedMonth = $(this).data('month');
                    settings.selectedMonthName = $(this).text();
                    monthpicker.trigger('monthpicker-click-month', $(this).data('month'));
                }
            });

            container.find('.mtz-monthpicker-year').bind('change', function () {
                settings.selectedYear = $(this).val();
                monthpicker.trigger('monthpicker-change-year', $(this).val());
                $("tbody.mtz-monthpicker td.mtz-monthpicker-month").each(function(){
                	$(this).html("");
                	methods.createMonthElement($(this), $(this).data('month'), settings)
                });
            });

            container.appendTo('body');
        },
        
        createMonthElement: function(td, monthNumber, settings){
        	
            if ($.inArray(monthNumber, settings.disabledMonths) >= 0 ||
            		!methods.dataValido(monthNumber, settings)) {
            	td.addClass("ui-datepicker-unselectable ui-state-disabled");
            	span = "<span class=\"ui-state-default\">" + settings.monthNames[monthNumber-1] +  "</span>"
            	td.append(span);
            }else{
            	td.removeClass("ui-datepicker-unselectable ui-state-disabled");
            	a = "<a class=\"ui-state-default\" href=\"#\">" + settings.monthNames[monthNumber-1] + "</a>"
            	td.append(a);
                td.find("a").mouseover( function(){
                	$(this).addClass('ui-state-hover');
                });
                td.find("a").mouseout( function(){
                	$(this).removeClass('ui-state-hover');
                });
            }
        },

        dataValido : function(mes, settings){
    		var data = ("00" + mes).substr(("00" + mes).length - 2, ("00" + mes).length) + "/" + settings.selectedYear;
    		if(!settings.validMonths || settings.validMonths.length == 0 || $.inArray(data, settings.validMonths) >= 0){
    			return true;
    		}
        	return false;
        },
        
        destroy: function () {
            return this.each(function () {
            	$(this).unbind('focus', this.show);
            	$(this).unbind('blur', this.hide);
            	$('#' + $(this).data('monthpicker').settings.id.replace(":", "\\:")).remove();
                $(this).removeData('monthpicker');
            });
        }

    };

    $.fn.monthpicker = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call( arguments, 1 ));
        } else if (typeof method === 'object' || ! method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.mtz.monthpicker');
        }    
    };

})(jQuery);