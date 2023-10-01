import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:integrations_flutter/platform/platform_view_widget.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final _textController = TextEditingController();
  final platform = const MethodChannel('CALL_METHOD');

  Future<void> setValue(String message) async {
    try {
      print(message);
      await platform.invokeMethod("setText", message);
      setState(() {});
    } on PlatformException catch (e) {
      print(e.message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Center(
              child: Padding(
                padding: EdgeInsets.symmetric(vertical: 20.0),
                child: PlatformWidget(),
              ),
            ),
            SizedBox(
              height: 100,
              child: Row(
                children: <Widget>[
                  Flexible(
                    flex: 3,
                    child: TextField(
                      controller: _textController,
                      decoration: const InputDecoration(hintText: 'Text'),
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () => setValue(_textController.text),
                    child: const Text('Submit'),
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
