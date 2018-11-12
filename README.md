# react-native-example
Example Code for usage from React Native

## iOS Integration

1. Start with the [iOS Quick Integration](https://docs.embrace.io/docs/ios-integration-guide) in order to add and initialize the Embrace SDK.
1. Copy [EmbraceManager.h](/EmbraceManager.h) and [EmbraceManager.m](/EmbraceManager.m) into the `ios` React Native folder. This [guide](https://shift.infinite.red/beginner-s-guide-to-using-cocoapods-with-react-native-46cb4d372995) gives an example of placement and CocoaPod configuration.
1. Copy [embrace.js](/embrace.js) into your JS codebase. Once complete, methods can be imported and called from this file.
1. End your startup moment. This moment begins automatically with the code added to `AppDelegate.m`, and should be ended on componenent mount similar to the below code.

```js
import {endAppStartup} from './embrace';

type Props = {};
export default class App extends Component<Props> {
  componentDidMount() {
    endAppStartup();
  }
}
```
