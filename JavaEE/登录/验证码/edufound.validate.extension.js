jQuery.extend(jQuery.validator.classRuleSettings, {
	password: {password:true},
	nickName: {nickName:true},
	selectRequired:{selectRequired:true},
	decimal2:{decimal2:true},
	imgSuffix:{imgSuffix:true},
	swfSuffix:{swfSuffix:true},
	gradeRequired:{gradeRequired:true},
	phone:{phone:true},
	account:{account:true}
});
jQuery.extend(jQuery.validator.methods, {
    password: function(value, element){
      return this.optional(element) || /^\w{8,12}$/.test(value);
    },
    nickName:function(value,element) {
        return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]{2,16}$/.test(value);
    },
    selectRequired:function(value,element) {
    	var v_value = /^\d+$/.test(value);
    	 return this.optional(element) || v_value>0;
    },
    decimal2:function(value, element){
		return this.optional(element) || /^(([1-9]\d*)|0)*(\.\d{1,2})?$/.test(value);
	},
	imgSuffix:function(value,element) {
		var v_value = value.split('.');
		return this.optional(element) || /^(jpg|gif|jpeg)$/.test(v_value[1].toLowerCase());
	},
	swfSuffix:function(value,element) {
		var v_value = value.split('.');
		return this.optional(element) || /^(swf)$/.test(v_value[1].toLowerCase());
	},
	gradeRequired:function(value,element){
		return this.optional(element) || value.indexOf("_")!=-1;
	},
	phone:function(value, element){
		return this.optional(element) || /^((0\d{2,3}-){0,1}\d{6,8}(-\d{1,4}){0,1})|(0{0,1}1[0-9]{10})$/.test(value);
	},
	account:function(value, element){
		return this.optional(element) || /^[a-zA-Z0-9-]{4,16}$/.test(value);
	}

});