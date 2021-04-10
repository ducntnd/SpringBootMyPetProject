var i=0;
$('.shows').on('click',function(){
	if(i==0){
		$('.disnone').css('display','flex');
		$('.shows').html('Ẩn đi')
		i++
	}else{
		$('.disnone').css('display','none');
		$('.shows').html('Hiện thêm')
		i--
	}
})
 var windowsize = $(window).width();
  if(windowsize<=768){
	if($('.video a').length %2 == 0){
		$('.video a:nth-last-child(2)').removeClass("disnone")
	}else{
		$('.video a:nth-last-child(2)').addClass("disnone")
	}
	$('.list-group').removeClass('list-group-horizontal')
	}else{
		if($('.video a').length %3 == 0){
		$('.video a:nth-last-child(2)').addClass("disnone")
	}else{
		$('.video a:nth-last-child(2)').removeClass("disnone")
	}
	$('.list-group').addClass('list-group-horizontal')
	}
$(window).resize(function() {
	var windowsizes = $(window).width();
  if(windowsizes<=768){
  	$('.list-group').removeClass('list-group-horizontal')
	if($('.video a').length %2 == 0){
		$('.video a:nth-last-child(2)').removeClass("disnone")
	}else{
		$('.video a:nth-last-child(2)').addClass("disnone")
	}
	}else{
		$('.video a:nth-last-child(2)').addClass("disnone")
		$('.list-group').addClass('list-group-horizontal')
	}
	
});

$(window).scroll(function() {
if($(window).scrollTop()<300){
	$('.fixed').css('visibility','hidden')
}else{
	$('.fixed').css('visibility','visible')
}
})

$('.fixed').on('click',function(){
	$("html, body").animate({scrollTop:"0"},500);
})
var x=0

$('.click').on('click',function(){
	if(x==0){
	$('.schedule').css('display','block')
	$('.click i').removeClass('fa-chevron-down')
	$('.click i').addClass('fa-chevron-up')
	x++
	}else{
	$('.schedule').css('display','none')
	$('.click i').removeClass('fa-chevron-up')
	$('.click i').addClass('fa-chevron-down')
	x--		
	}
})

var daychosen=0;
var date = new Date("Wednesday, January 08, 2020");
function compareDate(a,b){
	if(a.getYear()>b.getYear()){
		return 1
	}else if(a.getYear()<b.getYear()){
		return -1
	}else{
		if(a.getMonth()>b.getMonth()){
			return 1
		}else if(a.getMonth()<b.getMonth()){
			return -1
		}else{
			if(a.getDate()>b.getDate()){
				return 1
			}else if(a.getDate()<b.getDate()){
				return -1
			}else{
				return 0
			}
		}
	}
}
	var month=1
	var year=20
$('button .n').on('click',function(){
 	daychosen=Number($(this).text())
 	var newdate=new Date(`${month}/${daychosen}/20${year}`)
 	if(compareDate(newdate,date)==-1){
 		$('.list-group-item').eq(3).removeClass('border-left-0 border-right-0 underlined')
 		$('.list-group-item').eq(3).addClass('border-0')
 		$('.list-group-item').eq(4).removeClass('border-0')
 		$('.list-group-item').eq(4).addClass('border-left-0 border-right-0 underlined')
 	}
 	if(compareDate(newdate,date)>=0){
 		$('.list-group-item').eq(4).removeClass('border-left-0 border-right-0 underlined')
 		$('.list-group-item').eq(4).addClass('border-0')
 		$('.list-group-item').eq(3).removeClass('border-0')
 		$('.list-group-item').eq(3).addClass('border-left-0 border-right-0 underlined')
 	}
 		$(`.${daychosen}t${month}n${year}`).siblings().css('display','none')
 		$(`.${daychosen}t${month}n${year}`).css('display','block')
 		$(`.month-year`).html(`Tháng ${month}, năm 20${year}`)
})
$('button .n').removeClass('p-2')
$('.back').on('click',function(){

	if(month==1){
		year--
		month=12
	}else{
	month--
	}
	$(`.${month}${year}`).css('display','block')
	$(`.${month}${year}`).siblings().css('display','none')
	$(`.${month}-20${year}`).css('display','grid')
	$(`.${month}-20${year}`).siblings().css('display','none')
})
$('.next').on('click',function(){
	if(month==12){
		year++
		month=1
	}else{
	month++
	}
	$(`.${month}${year}`).css('display','block')
	$(`.${month}${year}`).siblings().css('display','none')
	$(`.${month}-20${year}`).css('display','grid')
	$(`.${month}-20${year}`).siblings().css('display','none')
})


