<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
<script type="text/javascript" src="/js/echarts.min.js"></script>
</head>
<body style="background-color: #F3F3F3">
		<div id="myChart" style="width: 600px;height:400px;"></div> 
    
</body>
    <script type="text/javascript">
		$(function(){
			
			 // 基于准备好的dom，初始化echarts实例 
		    var myChart = echarts.init(document.getElementById('myChart')); 
		     
		    option = { 
		        tooltip: { 
		            trigger: 'item',
		            formatter: "{a} <br/>{b}: {c} ({d}%)" 
		        }, 
		        legend: { 
		            orient: 'horizontal', 
		            left: 'center', 
		            bottom: 0, 
		            data:['直达','其它外链','搜索引擎','直接输入网址或书签','cnblogs.com','微博','微信','百度','谷歌','360','必应','其他'] 
		        }, 
		        series: [ 
		            { 
		                name:'访问来源', //内环 
		                type:'pie', 
		                selectedMode: 'single', //单一选中模式 
		                radius: [0, '30%'], //饼图的半径 [内半径，外半径] 
		     
		                label: { 
		                    normal: { 
		                        position: 'inner' //内置文本标签 
		                    } 
		                }, 
		                labelLine: { 
		                    normal: { 
		                        show: false     //不需要设置引导线 
		                    } 
		                }, 
		                data:[ 
		                    {value:335, name:'直达', selected:true}, 
		                    {value:679, name:'其它外链'}, 
		                    {value:1548, name:'搜索引擎'} 
		                ] 
		            }, 
		            { 
		                name:'访问来源', 
		                type:'pie', 
		                radius: ['40%', '55%'], 
		     
		                data:[ 
		                    {value:335, name:'直接输入网址或书签'}, 
		                    {value:310, name:'cnblogs.com'}, 
		                    {value:234, name:'微博'}, 
		                    {value:135, name:'微信'}, 
		                    {value:1048, name:'百度'}, 
		                    {value:251, name:'谷歌'}, 
		                    {value:147, name:'360'}, 
		                    {value:42, name:'必应'}, 
		                    {value:60, name:'其他'} 
		                ] 
		            } 
		        ] 
		    }; 
		     
		    // 使用刚指定的配置项和数据显示图表。 
		    myChart.setOption(option); 
		});
    </script>
</html>