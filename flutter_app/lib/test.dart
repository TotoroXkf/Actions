import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class GradientCircularProgressIndicator extends StatelessWidget {
	final double stokeWidth;
	final double radius;
	final bool stokeCapRound;
	final double value;
	final Color backgroundColor;
	final double totalAngle;
	final List<Color> colors;
	final List<double> stops;
	
	GradientCircularProgressIndicator({
		this.stokeWidth = 2.0,
		@required this.radius,
		@required this.colors,
		this.stops,
		this.stokeCapRound = false,
		this.backgroundColor = const Color(0xFFEEEEEE),
		this.totalAngle = 2 * pi,
		this.value,
	});
	
	@override
	Widget build(BuildContext context) {
		double offset = 0.0;
		
		if (stokeCapRound) {
			offset = asin(stokeWidth / (radius * 2 - stokeWidth));
		}
		var _colors = colors;
		if (_colors == null) {
			Color color = Theme
					.of(context)
					.accentColor;
			_colors = [color, color];
		}
		var widget = Transform.rotate(
			angle: -pi / 2.0 - offset,
			child: null,
		);
		return widget;
	}
	
}

class GradientCircularProgressPainter extends CustomPainter {
	final double stokeWidth;
	final bool strokeCapRound;
	final double value;
	final Color backgroundColor;
	final List<Color> colors;
	final double total;
	final double radius;
	final List<double> stops;
	
	GradientCircularProgressPainter({
		this.stokeWidth: 10.0,
		this.strokeCapRound: false,
		this.backgroundColor = const Color(0xFFEEEEEE),
		this.radius,
		this.total = 2 * pi,
		@required this.colors,
		this.stops,
		this.value
	});
	
	@override
	void paint(Canvas canvas, Size size) {
		if (radius == null) {
			size = Size.fromRadius(radius);
		}
		
		double _offset = stokeWidth / 2.0;
		double _value = (value ?? .0);
		_value = _value.clamp(.0, 1.0) * total;
		double _start = .0;
		
		if (strokeCapRound) {
			_start = asin(stokeWidth / (size.width - stokeWidth));
		}
		
		Rect rect = Offset(_offset, _offset) & Size(
				size.width - stokeWidth, size.height - stokeWidth);
		
	}
	
	@override
	bool shouldRepaint(CustomPainter oldDelegate) {
		return null;
	}
}